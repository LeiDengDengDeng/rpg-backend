package com.design.rpg.model;

import com.design.rpg.form.MonsterType;
import com.design.rpg.model.builder.Equipment;
import lombok.Data;

import java.util.List;

/**
 * @author deng
 * @date 2019/03/04
 */
@Data
public class MonsterModel extends Creature {
   private int atk;
   private int def;
   private int winExp;
   private int winMoney;
   private MonsterType monsterType;
   private List<Equipment> winEquipment;

   public void attack(HumanModel humanModel){
         humanModel.setHp(humanModel.getHp()-(this.getAtk()-humanModel.getDef()>0?this.getAtk()-humanModel.getDef():0));
   }


}
