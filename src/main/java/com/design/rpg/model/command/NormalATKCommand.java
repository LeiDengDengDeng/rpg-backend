package com.design.rpg.model.command;


import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

import java.util.Map;

/**
 * Created by liying on 2019/3/28.
 */
public class NormalATKCommand implements HumanATKCommand {


    @Override
    public void calculateATK(HumanModel humanModel, MonsterModel monsterModel) {
        Map<Class,Integer> cdMap=humanModel.getMyCDMap();
        int attack=humanModel.getAtk();
        int defence=monsterModel.getDef();
        int hurt=attack>defence?attack-defence:0;
        monsterModel.setHp(monsterModel.getHp()-hurt);
        cdMap.replaceAll((k,v)->v==0?v:v-1);

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
