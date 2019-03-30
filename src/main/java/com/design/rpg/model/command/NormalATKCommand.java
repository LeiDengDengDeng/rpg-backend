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

    @Override
    public String getDescription() {
        return "对敌人造成少量伤害";
    }

    @Override
    public int getCD() {
        return 0;
    }

    @Override
    public char getBindKey() {
        return 'H';
    }
}
