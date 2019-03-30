package com.design.rpg.model.stragety;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * Created by liying on 2019/3/30.
 */
public class HardMonsterStrategy implements MonsterGenerateStrategy{
    @Override
    public MonsterModel createMonster(HumanModel humanModel) {
        MonsterModel monsterModel=new MonsterModel();
        monsterModel.setHP((int)(humanModel.getHP()*1.2));
        monsterModel.setMaxHP((int)(humanModel.getHP()*1.2));
        monsterModel.setATK((int)(humanModel.getDEF()*1.8));
        monsterModel.setDEF((int)(humanModel.getATK()*0.9));
        monsterModel.setWinMoney((int)(Math.random()*20*humanModel.getLevel()));
        monsterModel.setWinExp((int)(Math.random()*0.1*humanModel.getLevelExp()*3));


        return monsterModel;
    }
}
