package com.design.rpg.model;

import com.design.rpg.model.command.HumanATKCommand;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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

    private Map<Class,Integer> cdMap;
    private Map<Class,Integer> myCDMap;

    public HumanModel(){
        cdMap=new HashMap<>();
        myCDMap=new HashMap<>();
        init();
    }

    // HP等的计算方式与属性有关

    // 背包
    // 身上的装备
    private int money;

    abstract  public void init();

    abstract public void attack(MonsterModel monsterModel, HumanATKCommand humanATKCommand);

    final public void expUp(int exp) {
        curLevelExp+=exp;
        //当前等级需要经验
        int levelExp=this.getLevelExp();
        int leftExp=curLevelExp-levelExp;
        if(leftExp>=0){
            levelUp(leftExp);
        }
    }

    abstract public void levelUp(int exp);


    public void revive() {
        this.setHP(this.getMaxHP()*20);
        this.setMoney((int)(Math.random()*this.level*10));
    }
    @Override
    public int getATK(){
        return (power+agility+spirit+physique+endurance)*3;
    }
    @Override
    public int getDEF(){
        return (physique+endurance)*3;
    }
    public int getLevelExp(){
        return (int)Math.pow(level,2)*10;
    }

}
