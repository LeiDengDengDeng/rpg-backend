package com.design.rpg.model;

import lombok.Data;

/**
 * 人物
 *
 * @author deng
 * @date 2019/03/04
 */
@Data
public class HumanModel extends Creature {
    private int level;
    private int curLevelExp;

    private int power;      // 力量
    private int agility;    // 敏捷
    private int spirit;     // 精神
    private int physique;   // 体质
    private int endurance;  // 耐力
    private int luck;       // 幸运

    // HP等的计算方式与属性有关

    // 背包
    // 身上的装备

    private int money;

    public void revive() {

    }
}
