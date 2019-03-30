package com.design.rpg.vo;

import lombok.Getter;

/**
 * @author deng
 * @date 2019/03/29
 */
@Getter
public class WaitingStateInfoVO extends StateInfoVO {
    public WaitingStateInfoVO() {
        this.state = GameStateEnum.WAITING;
        this.curLog = "等待人物攻击中...\n";
    }
}
