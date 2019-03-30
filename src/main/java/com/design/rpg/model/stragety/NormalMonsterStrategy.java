package com.design.rpg.model.stragety;

import com.design.rpg.form.MonsterType;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * Created by liying on 2019/3/30.
 */
public class NormalMonsterStrategy implements MonsterGenerateStrategy{
    @Override
    public MonsterModel createMonster(HumanModel humanModel) {
        MonsterModel monsterModel=new MonsterModel();
        monsterModel.setHP((int)(humanModel.getMaxHP()*0.8));
        monsterModel.setMaxHP((int)(humanModel.getMaxHP()*0.8));
        monsterModel.setATK((int)(humanModel.getDEF()*1.4));
        monsterModel.setDEF((int)(humanModel.getATK()*0.6));
        //6-12
        monsterModel.setWinMoney((int)(6+Math.random()*7));
        monsterModel.setWinExp((int)(6+Math.random()*7));
        monsterModel.setMonsterType(MonsterType.NORMAL);
        return monsterModel;
    }
}
