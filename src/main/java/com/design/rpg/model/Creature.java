package com.design.rpg.model;

import lombok.Data;

/**
 * 生物，基本属性：生命值，攻击力，防御力
 *
 * @author deng
 * @date 2019/03/24
 */
@Data
public abstract class Creature {
    private int maxHP;  // 最大生命值
    private int HP;     // 当前生命值
    private int ATK;    // 攻击力
    private int DEF;    // 防御力
}
