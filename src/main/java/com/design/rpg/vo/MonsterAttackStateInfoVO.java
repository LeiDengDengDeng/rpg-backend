package com.design.rpg.vo;

import lombok.Getter;

/**
 * @author deng
 * @date 2019/03/29
 */
@Getter
public class MonsterAttackStateInfoVO extends StateInfoVO {
    private int humanHPChange;

    public MonsterAttackStateInfoVO(int humanHPChange) {
        this.state = GameStateEnum.ATTACKED;
        this.humanHPChange = humanHPChange;
    }
}
