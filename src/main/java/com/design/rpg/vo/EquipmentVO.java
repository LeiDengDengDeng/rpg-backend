package com.design.rpg.vo;

import com.design.rpg.form.ColorType;
import com.design.rpg.form.EquipmentType;
import lombok.Data;

/**
 * @author deng
 * @date 2019/03/31
 */
@Data
public class EquipmentVO {
    private String uuid;
    private int levelUpTimes;
    private int power;      // 力量
    private int agility;    // 敏捷
    private int spirit;     // 精神
    private int physique;   // 体质
    private int endurance;  // 耐力
    private int luck;       // 幸运
    private String name;
    private EquipmentType type;
    private String description;
    private String url;
    private ColorType colorType;
    private int splitMoney;
    private int strengthMoney;
}
