package com.design.rpg.model.state;

import com.design.rpg.model.command.HumanATKCommand;

/**
 * 游戏状态：闲逛中，等待攻击中，阻塞中
 *
 * @author deng
 * @date 2019/03/04
 */
public interface GameState {
    void move();

    void humanAttack(HumanATKCommand strategy);
}
