package com.design.rpg.vo;

import lombok.Getter;

/**
 * @author deng
 * @date 2019/03/29
 */
@Getter
public class HumanWinStateInfoVO extends HumanAttackStateInfoVO {
    private int moneyChange;
    private int expUp;

    public HumanWinStateInfoVO(int humanHPChange, int monsterHPChange, int moneyChange, int expUp) {
        super(humanHPChange, monsterHPChange);
        this.state = GameStateEnum.WIN;
        this.moneyChange = moneyChange;
        this.expUp = expUp;
    }
}
