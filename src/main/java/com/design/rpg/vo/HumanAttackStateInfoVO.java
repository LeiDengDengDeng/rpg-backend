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

        this.curLog = "你攻击了怪物，对怪物造成了" + (-monsterHPChange) + "点伤害！\n";
        if (humanHPChange > 0) {
            this.curLog += "同时你恢复了自身" + humanHPChange + "点血量！\n";
        }
    }
}
