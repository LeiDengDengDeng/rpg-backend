package com.design.rpg.vo;

import lombok.Getter;

/**
 * @author deng
 * @date 2019/03/29
 */
@Getter
public class HumanAttackStateInfoVO extends StateInfoVO {
    private int humanHPChange;
    private int monsterHPChange;

    public HumanAttackStateInfoVO(int humanHPChange, int monsterHPChange) {
        this.state = GameStateEnum.ATTACKING;
        this.humanHPChange = humanHPChange;
        this.monsterHPChange = monsterHPChange;
    }
}
