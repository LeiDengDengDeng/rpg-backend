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
    private int hpUp;

    public HumanWinStateInfoVO(int humanHPChange, int monsterHPChange, int moneyChange, int expUp, boolean levelUpFlag, int hpUp) {
        super(humanHPChange, monsterHPChange);
        this.state = GameStateEnum.WIN;
        this.moneyChange = moneyChange;
        this.expUp = expUp;
        this.hpUp = hpUp;

        this.curLog = "你攻击了怪物，对怪物造成了" + (-monsterHPChange) + "点伤害！\n";
        if (humanHPChange > 0) {
            this.curLog += "同时你恢复了自身" + humanHPChange + "点血量\n！";
        }
        this.curLog += "怪物被击败了！你获得了" + moneyChange + "钱和" + expUp + "点经验值，并恢复了" + hpUp + "点血量\n";
        if (levelUpFlag) {
            this.curLog += "恭喜你升级了！";
        }
    }
}
