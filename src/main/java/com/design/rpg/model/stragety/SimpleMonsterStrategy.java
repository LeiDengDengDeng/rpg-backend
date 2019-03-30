package com.design.rpg.model.stragety;

import com.design.rpg.form.MonsterType;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * Created by liying on 2019/3/30.
 */
public class SimpleMonsterStrategy implements MonsterGenerateStrategy{
    @Override
    public MonsterModel createMonster(HumanModel humanModel) {
        MonsterModel monsterModel=new MonsterModel();
        monsterModel.setHP((int)(humanModel.getMaxHP()*0.5));
        monsterModel.setMaxHP((int)(humanModel.getMaxHP()*0.5));
        monsterModel.setATK((int)(humanModel.getDEF()*1.1));
        monsterModel.setDEF((int)(humanModel.getATK()*0.3));
        //2-5
        monsterModel.setWinExp((int)(2+Math.random()*4));
        //2-5
        monsterModel.setWinMoney((int)(2+Math.random()*4));
        monsterModel.setMonsterType(MonsterType.SIMPLE);
        return monsterModel;
    }
}
