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
        this.curLog = "人物受到怪物攻击，减少" + (-humanHPChange) + "点血量!\n";
    }
}
