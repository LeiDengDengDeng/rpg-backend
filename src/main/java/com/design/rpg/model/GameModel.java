package com.design.rpg.model;

import com.design.rpg.controller.WebSocketServer;
import com.design.rpg.exception.ServiceException;
import com.design.rpg.model.command.*;
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

    @Getter
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
        this.monsterModel = null;

        return InfoVOFactory.createInfoVO(StateInfoVOFactory.createMoveStateInfoVO(), this.humanModel, this.monsterModel);
    }

    public void move() {
        AssertUtil.assertNotNull(humanModel, ServiceException.NOT_EXIST);
        curState.move();
    }

    public void humanAttack(char key) {
        AssertUtil.assertNotNull(humanModel, ServiceException.NOT_EXIST);

        switch (key) {
            case 'H':
                curState.humanAttack(new NormalATKCommand());
                return;
            case 'J':
                switch (humanModel.getHumanType()) {
                    case DOCTOR:
                        curState.humanAttack(new DoctorFirstSkillATKCommand());
                        return;
                    case SOLDIER:
                        curState.humanAttack(new SoldierFirstSkillATKCommand());
                        return;
                    case ASSASSIN:
                        curState.humanAttack(new AssassanFirstSkillATKCommand());
                        return;
                    default:
                        throw new ServiceException(ServiceException.KEY_NOT_MATCHED);
                }
            case 'K':
                switch (humanModel.getHumanType()) {
                    case DOCTOR:
                        curState.humanAttack(new DoctorSecondSkillATKCommand());
                        return;
                    case SOLDIER:
                        curState.humanAttack(new SoldierSecondSkillATKCommand());
                        return;
                    case ASSASSIN:
                        curState.humanAttack(new AssassanSecondSkillATKCommand());
                        return;
                    default:
                        throw new ServiceException(ServiceException.KEY_NOT_MATCHED);
                }
            case 'L':
                switch (humanModel.getHumanType()) {
                    case DOCTOR:
                        curState.humanAttack(new DoctorThirdSkillATKCommand());
                        return;
                    case SOLDIER:
                        curState.humanAttack(new SoldierThirdSkillATKCommand());
                        return;
                    case ASSASSIN:
                        curState.humanAttack(new AssassanThirdSkillATKCommand());
                        return;
                    default:
                        throw new ServiceException(ServiceException.KEY_NOT_MATCHED);
                }
            default:
                throw new ServiceException(ServiceException.KEY_NOT_MATCHED);
        }
    }

    public void humanAttackMonster(HumanATKCommand strategy) {
        curState = blockState;

        int humanHPChange = humanModel.getHp();
        int monsterHPChange = monsterModel.getHp();

        humanModel.attack(monsterModel, strategy);

        // 计算人物出招过程中血量变化
        humanHPChange = humanModel.getHp() - humanHPChange;
        monsterHPChange = monsterModel.getHp() - monsterHPChange;

        if (monsterModel.getHp() <= 0) {
            // 增长经验，如果经验满了还要升级，升级了还要涨技能点，在humanModel实现Exp++功能，而不是简单的set
            boolean levelUpFlag = humanModel.expUp(monsterModel.getWinExp());
            // 适当恢复人物一些HP
            int recoverHP = (int) (humanModel.getMaxHP() * 0.1) + humanModel.getHp();
            int hpUp = 0;
            if (recoverHP > humanModel.getMaxHP()) {
                hpUp = humanModel.getMaxHP() - humanModel.getHp();
                humanModel.setHp(humanModel.getMaxHP());
            } else {
                hpUp = recoverHP - humanModel.getHp();
                humanModel.setHp(recoverHP);
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

        int humanHPChange = humanModel.getHp();

        // 怪物攻击人
        monsterModel.attack(humanModel);

        humanHPChange = humanModel.getHp() - humanHPChange;

        if (humanModel.getHp() <= 0) {
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
