package com.design.rpg.model.state;

import com.design.rpg.model.strategy.HumanATKStrategy;

/**
 * 游戏状态：闲逛中，Role攻击中，Monster攻击中，复活中
 *
 * @author deng
 * @date 2019/03/04
 */
public interface GameState {
    void move();

    void humanAttack(HumanATKStrategy strategy);
}
