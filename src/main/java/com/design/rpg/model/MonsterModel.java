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
   private int ATK;
   private int DEF;
   private int winExp;
   private int winMoney;
   private MonsterType monsterType;
   private List<Equipment> winEquipment;

   public void attack(HumanModel humanModel){
         humanModel.setHP(humanModel.getHP()-(this.getATK()-humanModel.getDEF()>0?this.getATK()-humanModel.getDEF():0));
   }


}
