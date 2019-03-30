package com.design.rpg.model.builder;

import com.design.rpg.form.ColorType;
import com.design.rpg.form.EquipmentType;
import lombok.Data;

/**
 * @author deng
 * @date 2019/03/04
 */
@Data
public abstract class Equipment {
//    private EquipmentType equipmentType;
//    private MainAttrType mainAttrType;   // ATK/DEF

    String uuid;
    int levelUpTimes;
    int power;      // 力量
    int agility;    // 敏捷
    int spirit;     // 精神
    int physique;   // 体质
    int endurance;  // 耐力
    int luck;       // 幸运
    String name;
    EquipmentType type;
    String description;
    String url;
    ColorType colorType;

    public Equipment(){
        this.levelUpTimes=0;
    }

    public boolean levelUp(){
        double random=Math.random();
        if(levelUpTimes==0||random<1/levelUpTimes){
            if(Math.random()<0.5){
                power=power+2;
            }
            if(Math.random()<0.5){
                agility=agility+2;
            }
            if(Math.random()<0.5){
                spirit=spirit+2;
            }
            if(Math.random()<0.5){
                physique=physique+2;
            }
            if(Math.random()<0.5){
                endurance=endurance+2;
            }if(Math.random()<0.5){
                luck=luck+2;
            }
            levelUpTimes++;
            return true;
        }else{
            return false;
        }
    }
    public int splitUp(){
        return power+agility+spirit+physique+endurance+luck;
    }
    public int getATK(){
        return power+agility+spirit+physique+endurance;
    }
    public int getDEF(){
        return endurance;
    }
    public int getHP(){return physique;}
    public int getMoneyPayed(){
        return (int)Math.pow(levelUpTimes,2)*10;
    }

}
