package com.design.rpg.model.stragety;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * Created by liying on 2019/3/30.
 */
public class SimpleMonsterStrategy implements MonsterGenerateStrategy{
    @Override
    public MonsterModel createMonster(HumanModel humanModel) {
        MonsterModel monsterModel=new MonsterModel();
        monsterModel.setHP((int)(humanModel.getHP()*0.5));
        monsterModel.setMaxHP((int)(humanModel.getHP()*0.5));
        monsterModel.setATK((int)(humanModel.getDEF()*1.1));
        monsterModel.setDEF((int)(humanModel.getATK()*0.3));
        monsterModel.setWinExp((int)(Math.random()*0.1*humanModel.getLevelExp()*1.2));
        monsterModel.setWinMoney((int)(Math.random()*10*humanModel.getLevel()));

        return monsterModel;
    }
}
