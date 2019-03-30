package com.design.rpg.model.state;

import com.design.rpg.model.GameModel;
import com.design.rpg.model.command.HumanATKCommand;
import com.design.rpg.vo.factory.StateInfoVOFactory;

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

        if (Math.random() > 0.3) {
            gameModel.createMonster();
            gameModel.setCurState(gameModel.getHumanAttackState());

            // 前端变成等待用户攻击状态
            gameModel.sendMessage(StateInfoVOFactory.createWaitingStateInfoVO());
        } else {
            gameModel.setCurState(gameModel.getMoveState());

            // 前端变成依旧是等待用户移动状态
            gameModel.sendMessage(StateInfoVOFactory.createMoveStateInfoVO());
        }
    }

    @Override
    public void humanAttack(HumanATKCommand strategy) {

    }
}
