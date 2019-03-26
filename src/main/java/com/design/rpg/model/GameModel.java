package com.design.rpg.model;

import com.design.rpg.model.state.BlockState;
import com.design.rpg.model.state.GameState;
import com.design.rpg.model.state.HumanAttackState;
import com.design.rpg.model.state.MoveState;
import com.design.rpg.model.strategy.HumanATKStrategy;

/**
 * @author deng
 * @date 2019/03/27
 */
public class GameModel implements AbstractGame {
    private HumanModel humanModel;
    private MonsterModel monsterModel;


    private GameState moveState;            // 只支持move操作的状态
    private GameState humanAttackState;     // 只支持用户攻击的状态
    private GameState blockState;           // 所有操作都不支持的状态


    private GameState curState; // 游戏当前状态

    public GameModel() {
        // TODO:持久化要考虑load和save，目前先不考虑持久化
        this.humanModel = new HumanModel();
        this.monsterModel = new MonsterModel();


        this.moveState = new MoveState();
        this.humanAttackState = new HumanAttackState(this);
        this.blockState = new BlockState();

        // 初始状态为移动中
        this.curState = this.moveState;
    }

    @Override
    public void move() {
        curState.move();
        // 这里要负责创建MonsterModel
    }

    @Override
    public void humanAttack(HumanATKStrategy strategy) {
        curState.humanAttack(strategy);
    }


    public void humanAttackMonster(int humanATK) {
        curState = blockState;

        monsterModel.setHP(monsterModel.getHP() - (humanATK - monsterModel.getDEF()));

        // 改变状态  结算 or 怪物攻击
        if (monsterModel.getHP() <= 0) {
            monsterModel = null;

            // TODO:结算的完善
            // 增长经验，如果经验满了还要升级，升级了还要涨技能点，在humanModel实现Exp++功能，而不是简单的set
            humanModel.setCurLevelExp(humanModel.getCurLevelExp() + 10);
            // 适当恢复人物一些HP
            humanModel.setHP(humanModel.getHP() + 100);
            // 随机掉落物品和金钱
            humanModel.setMoney(humanModel.getMoney() + 100);
            // humanModel.addItem()...


            curState = moveState;
        } else {
            // 人物攻击结束，轮到monster攻击
            // ThreadSleep一会，模拟怪物攻击的等待时间
            monsterAttackHuman(monsterModel.getATK());
        }
    }

    public void monsterAttackHuman(int monsterATK) {
        curState = blockState;

        humanModel.setHP(humanModel.getHP() - (monsterATK - humanModel.getDEF()));

        // 复活 or 人物攻击
        if (humanModel.getHP() <= 0) {
            monsterModel = null;

            // TODO:复活
            // 写成每1秒回复1/5的血量，5秒复活完成
            humanModel.setHP(humanModel.getMaxHP());
            // 掉钱，甚至掉物品
            humanModel.setMoney(humanModel.getMoney() - 100);

            curState = moveState;
        } else {
            curState = humanAttackState;
        }
    }
}
