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
        this.humanHPChange = humanHPChange;
        this.moneyChange = moneyChange;
        this.curLog = "人物受到怪物攻击，减少" + (-humanHPChange) + "点血量!\n人物死亡了...人物损失" + (-moneyChange) + "钱\n";
    }
}
