package com.design.rpg.vo;

import lombok.Getter;

/**
 * @author deng
 * @date 2019/03/29
 */
@Getter
public class MoveStateInfoVO extends StateInfoVO {
    public MoveStateInfoVO() {
        this.state = GameStateEnum.MOVING;
    }
}
