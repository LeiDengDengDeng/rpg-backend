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

   public void attack(HumanModel humanModel){

   }

}
