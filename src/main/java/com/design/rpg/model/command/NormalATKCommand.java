package com.design.rpg.model.command;


import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * Created by liying on 2019/3/28.
 */
public class NormalATKCommand implements HumanATKCommand {


    @Override
    public void calculateATK(HumanModel humanModel, MonsterModel monsterModel) {
        int attack=humanModel.getATK();
        int defence=monsterModel.getDEF();
        int hurt=attack>defence?attack-defence:0;
        monsterModel.setHP(monsterModel.getHP()-hurt);

    }

    @Override
    public String getName() {
        return "普攻";
    }
}
