package com.design.rpg.model.strategy;


import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * Created by liying on 2019/3/28.
 */
public class NormalATKStrategy implements HumanATKStrategy {


    @Override
    public void calculateATK(HumanModel humanModel, MonsterModel monsterModel) {
        int attack=humanModel.getPower();
        int defence=monsterModel.getDEF();
        int hurt=attack>defence?attack-defence:0;
        monsterModel.setHP(monsterModel.getHP()-hurt);

    }
}
