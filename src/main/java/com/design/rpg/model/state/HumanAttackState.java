package com.design.rpg.model.state;

import com.design.rpg.model.GameModel;
import com.design.rpg.model.strategy.HumanATKStrategy;

/**
 * @author deng
 * @date 2019/03/27
 */
public class HumanAttackState implements GameState {
    private GameModel gameModel;

    public HumanAttackState(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void move() {
        // TODO:当前状态不支持移动  抛异常 or empty方法体
    }

    @Override
    public void humanAttack(HumanATKStrategy strategy) {
        int humanATK = strategy.calculateATK();
        gameModel.humanAttackMonster(humanATK);
        // 在GameModel中定义humanAttackMonster方法 or 把humanAttackMonster的代码放在这？
        // 如果是后者，则该类会持有对Human & Monster的引用
        // 如果是前者，可以减少耦合，但GameModel职责变重
        // 目前用的后者
    }
}
