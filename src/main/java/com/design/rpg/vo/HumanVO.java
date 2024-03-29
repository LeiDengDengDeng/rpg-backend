package com.design.rpg.vo;

import com.design.rpg.form.HumanType;
import com.design.rpg.model.builder.Role;
import lombok.Data;

import java.util.List;

/**
 * @author deng
 * @date 2019/03/30
 */
@Data
public class HumanVO {
    private int maxHP;  // 最大生命值
    private int hp;     // 当前生命值

    private int atk;
    private int def;

    private int level;
    private int curLevelExp;
    private int curLevelMaxExp;

    private int power;      // 力量
    private int agility;    // 敏捷
    private int spirit;     // 精神
    private int physique;   // 体质
    private int endurance;  // 耐力
    private int luck;       // 幸运

    private int money;

    private HumanType humanType;

    private List<EquipmentVO> bag;    //背包
    private Role role;              //身上的装备

    private List<SkillVO> skills;
}
