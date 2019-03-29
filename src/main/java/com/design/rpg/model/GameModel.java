package com.design.rpg.model;

import com.design.rpg.model.state.BlockState;
import com.design.rpg.model.state.GameState;
import com.design.rpg.model.state.HumanAttackState;
import com.design.rpg.model.state.MoveState;
import com.design.rpg.model.strategy.HumanATKStrategy;
import lombok.Getter;
import lombok.Setter;

/**
 * @author deng
 * @date 2019/03/27
 */
public class GameModel {
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
//        this.humanModel = new HumanModel();

        this.moveState = new MoveState(this);
        this.humanAttackState = new HumanAttackState(this);
        this.blockState = new BlockState();

        // 初始状态为移动中
        this.curState = this.moveState;
    }

    public void move() {
        curState.move();
        // TODO:ws返回消息给前端
    }

    public void humanAttack(HumanATKStrategy strategy) {
        curState.humanAttack(strategy);
        // TODO:ws返回消息给前端
    }

    public void humanAttackMonster(HumanATKStrategy strategy) {
        curState = blockState;

        humanModel.attack(monsterModel, strategy);
        if (monsterModel.getHP() <= 0) {
            monsterModel = null;

            // TODO:结算的完善
            // 增长经验，如果经验满了还要升级，升级了还要涨技能点，在humanModel实现Exp++功能，而不是简单的set
            humanModel.expUp(100);
            // 适当恢复人物一些HP
            humanModel.setHP(humanModel.getHP() + 100);
            // 随机掉落物品和金钱
            humanModel.setMoney(humanModel.getMoney() + 100);
            // humanModel.addItem()...

            curState = moveState;

            // TODO:ws.send
        } else {
            // TODO:ws.send
            // 人物攻击结束，轮到monster攻击
            // ThreadSleep一会，模拟怪物攻击的等待时间
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monsterAttackHuman();
        }
    }

    public void monsterAttackHuman() {
        curState = blockState;

        monsterModel.attack(humanModel);
        if (humanModel.getHP() <= 0) {
            monsterModel = null;

            // TODO:ws.send
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            humanModel.revive();

            curState = moveState;
            // TODO:ws.send
        } else {
            curState = humanAttackState;
            // TODO:ws.send
        }
    }

    public void createMonster(){
        // TODO：需要根据人物的属性生成怪物
        this.monsterModel = new MonsterModel();
        monsterModel.setHP(1000);
        monsterModel.setMaxHP(1000);
        monsterModel.setATK(100);
        monsterModel.setDEF(100);
    }
}
