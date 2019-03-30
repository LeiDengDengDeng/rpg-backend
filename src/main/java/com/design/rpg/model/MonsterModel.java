package com.design.rpg.model;

import lombok.Data;

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

   public void attack(HumanModel humanModel){
         humanModel.setHP(this.getHP()-(this.getATK()-humanModel.getDEF()>0?this.getATK()-humanModel.getDEF():0));
   }


}
