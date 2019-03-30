package com.design.rpg.model.state;

import com.design.rpg.model.GameModel;
import com.design.rpg.model.command.HumanATKCommand;

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
    public void humanAttack(HumanATKCommand strategy) {
        gameModel.humanAttackMonster(strategy);
    }
}
