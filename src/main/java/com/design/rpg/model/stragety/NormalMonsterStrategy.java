package com.design.rpg.model.stragety;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * Created by liying on 2019/3/30.
 */
public class NormalMonsterStrategy implements MonsterGenerateStrategy{
    @Override
    public MonsterModel createMonster(HumanModel humanModel) {
        MonsterModel monsterModel=new MonsterModel();
        monsterModel.setHP((int)(humanModel.getHP()*0.8));
        monsterModel.setMaxHP((int)(humanModel.getHP()*0.8));
        monsterModel.setATK((int)(humanModel.getDEF()*1.4));
        monsterModel.setDEF((int)(humanModel.getATK()*0.6));
        monsterModel.setWinMoney((int)(Math.random()*50*humanModel.getLevel()));
        monsterModel.setWinExp((int)(Math.random()*0.1*humanModel.getLevelExp()*2.1));
        return monsterModel;
    }
}
