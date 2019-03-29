package com.design.rpg.model.state;

import com.design.rpg.model.GameModel;
import com.design.rpg.model.strategy.HumanATKStrategy;

/**
 * @author deng
 * @date 2019/03/27
 */
public class MoveState implements GameState {
    private GameModel gameModel;

    public MoveState(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void move() {
        gameModel.setCurState(gameModel.getBlockState());

        if (Math.random() > 0.5) {
            gameModel.createMonster();
            gameModel.setCurState(gameModel.getHumanAttackState());
        } else {
            gameModel.setCurState(gameModel.getBlockState());
        }
    }

    @Override
    public void humanAttack(HumanATKStrategy strategy) {

    }
}
