package com.design.rpg.model;

import com.design.rpg.model.strategy.HumanATKStrategy;

/**
 * @author deng
 * @date 2019/03/24
 */
public interface AbstractGame {
    void move();

    void humanAttack(HumanATKStrategy strategy);
}
