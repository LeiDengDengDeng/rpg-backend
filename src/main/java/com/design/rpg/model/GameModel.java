package com.design.rpg.model;

import com.design.rpg.controller.WebSocketServer;
import com.design.rpg.exception.ServiceException;
import com.design.rpg.model.command.HumanATKCommand;
import com.design.rpg.model.state.BlockState;
import com.design.rpg.model.state.GameState;
import com.design.rpg.model.state.HumanAttackState;
import com.design.rpg.model.state.MoveState;
import com.design.rpg.model.stragety.HardMonsterStrategy;
import com.design.rpg.model.stragety.NormalMonsterStrategy;
import com.design.rpg.model.stragety.SimpleMonsterStrategy;
import com.design.rpg.util.AssertUtil;
import com.design.rpg.vo.InfoVO;
import com.design.rpg.vo.StateInfoVO;
import com.design.rpg.vo.factory.InfoVOFactory;
import com.design.rpg.vo.factory.StateInfoVOFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author deng
 * @date 2019/03/27
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GameModel {
    @Setter
    private String userId;

    private HumanModel humanModel;
    private MonsterModel monsterModel;

    @Getter
    private GameState moveState;            // 只支持move操作的状态
    @Getter
    private GameState humanAttackState;     // 只支持用户攻击的状态
    @Getter
    private GameState blockState;           // 所有操作都不支持的状态
    @Setter
    private GameState curState;             // 游戏当前状态

    public GameModel() {
        // TODO:持久化要考虑load和save，目前先不考虑持久化
        this.moveState = new MoveState(this);
        this.humanAttackState = new HumanAttackState(this);
        this.blockState = new BlockState();

        // 初始状态为移动中
        this.curState = this.moveState;
    }

    public InfoVO loadHumanModel(String userId, HumanModel humanModel) {
        this.curState = this.moveState;

        this.userId = userId;
        this.humanModel = humanModel;

        return InfoVOFactory.createInfoVO(StateInfoVOFactory.createMoveStateInfoVO(), this.humanModel, this.monsterModel);
    }

    public void move() {
        AssertUtil.assertNotNull(humanModel, ServiceException.NOT_EXIST);
        curState.move();
    }

    public void humanAttack(HumanATKCommand strategy) {
        AssertUtil.assertNotNull(humanModel, ServiceException.NOT_EXIST);
        curState.humanAttack(strategy);
    }

    public void humanAttackMonster(HumanATKCommand strategy) {
        curState = blockState;

        int humanHPChange = humanModel.getHP();
        int monsterHPChange = monsterModel.getHP();

        humanModel.attack(monsterModel, strategy);

        // 计算人物出招过程中血量变化
        humanHPChange = humanModel.getHP() - humanHPChange;
        monsterHPChange = monsterModel.getHP() - monsterHPChange;

        if (monsterModel.getHP() <= 0) {
            // 增长经验，如果经验满了还要升级，升级了还要涨技能点，在humanModel实现Exp++功能，而不是简单的set
            boolean levelUpFlag = humanModel.expUp(monsterModel.getWinExp());
            // 适当恢复人物一些HP
            int recoverHP = (int) (humanModel.getMaxHP() * 0.1) + humanModel.getHP();
            int hpUp = 0;
            if (recoverHP > humanModel.getMaxHP()) {
                hpUp = humanModel.getMaxHP() - humanModel.getHP();
                humanModel.setHP(humanModel.getMaxHP());
            } else {
                hpUp = recoverHP - humanModel.getHP();
                humanModel.setHP(recoverHP);
            }
            // 随机掉落物品和金钱
            humanModel.setMoney(humanModel.getMoney() + monsterModel.getWinMoney());
            humanModel.getNewItems(monsterModel.getWinEquipment());

            sendMessage(StateInfoVOFactory.createHumanWinStateInfoVO(humanHPChange, monsterHPChange, monsterModel.getWinMoney(), monsterModel.getWinExp(), levelUpFlag, hpUp));
            monsterModel = null;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            curState = moveState;
            sendMessage(StateInfoVOFactory.createMoveStateInfoVO());
        } else {
            sendMessage(StateInfoVOFactory.createHumanAttackStateInfoVO(humanHPChange, monsterHPChange));

            // 人物攻击结束，轮到monster攻击
            monsterAttackHuman();
        }
    }

    protected void monsterAttackHuman() {
        curState = blockState;

        // ThreadSleep一会，模拟怪物攻击的等待时间
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int humanHPChange = humanModel.getHP();

        // 怪物攻击人
        monsterModel.attack(humanModel);

        humanHPChange = humanModel.getHP() - humanHPChange;

        if (humanModel.getHP() <= 0) {
            monsterModel = null;

            int moneyChange = humanModel.getMoney();
            humanModel.dead();
            moneyChange = humanModel.getMoney() - moneyChange;

            sendMessage(StateInfoVOFactory.createHumanReviveStateInfoVO(humanHPChange, moneyChange));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            humanModel.revive();

            curState = moveState;

            sendMessage(StateInfoVOFactory.createMoveStateInfoVO());
        } else {
            curState = humanAttackState;

            sendMessage(StateInfoVOFactory.createMonsterAttackStateInfoVO(humanHPChange));
            sendMessage(StateInfoVOFactory.createWaitingStateInfoVO());
        }
    }

    public void createMonster() {
        double random = Math.random();
        if (random < 0.6) {
            this.monsterModel = new SimpleMonsterStrategy().createMonster(this.humanModel);
        } else if (random < 0.9) {
            this.monsterModel = new NormalMonsterStrategy().createMonster(this.humanModel);
        } else {
            this.monsterModel = new HardMonsterStrategy().createMonster(this.humanModel);
        }
    }

    public void sendMessage(StateInfoVO stateInfoVO) {
        WebSocketServer.sendObject(userId, InfoVOFactory.createInfoVO(stateInfoVO, humanModel, monsterModel));
    }
}
