package com.design.rpg.model;

import com.design.rpg.model.strategy.HumanATKStrategy;
import lombok.Data;

/**
 * 人物
 *
 * @author deng
 * @date 2019/03/04
 */
@Data
public abstract class HumanModel extends Creature {
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

    abstract public void attack(MonsterModel monsterModel, HumanATKStrategy humanATKStrategy);

    final public void expUp(int exp) {
        curLevelExp+=exp;
        //当前等级需要经验
        int levelExp=(int)Math.pow(level,2);
        int leftExp=curLevelExp-levelExp;
        if(leftExp>=0){
            levelUp(leftExp);
        }
    }

    abstract public void levelUp(int exp);


    public void revive() {

    }
}
