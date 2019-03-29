package com.design.rpg.vo;

import lombok.Getter;

/**
 * @author deng
 * @date 2019/03/29
 */
@Getter
public class HumanReviveStateInfoVO extends MonsterAttackStateInfoVO {
    private int humanHPChange;
    private int moneyChange;

    public HumanReviveStateInfoVO(int humanHPChange, int moneyChange) {
        super(humanHPChange);
        this.state = GameStateEnum.REVIVE;
        this.moneyChange = moneyChange;
    }
}
