package com.design.rpg.vo;

import com.design.rpg.form.MonsterType;
import lombok.Data;

/**
 * @author deng
 * @date 2019/03/30
 */
@Data
public class MonsterVO {
    private int maxHP;  // 最大生命值
    private int hp;     // 当前生命值
    private int atk;
    private int def;
    private MonsterType monsterType;
}
