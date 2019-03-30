package com.design.rpg.model.stragety;

import com.design.rpg.form.MonsterType;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * Created by liying on 2019/3/30.
 */
public class HardMonsterStrategy implements MonsterGenerateStrategy{
    @Override
    public MonsterModel createMonster(HumanModel humanModel) {
        MonsterModel monsterModel=new MonsterModel();
        monsterModel.setHP((int)(humanModel.getMaxHP()*1.2));
        monsterModel.setMaxHP((int)(humanModel.getMaxHP()*1.2));
        monsterModel.setATK((int)(humanModel.getDEF()*1.8));
        monsterModel.setDEF((int)(humanModel.getATK()*0.9));
        monsterModel.setWinMoney((int)(13+Math.random()*9));
        monsterModel.setWinExp((int)(13+Math.random()*9));
        monsterModel.setMonsterType(MonsterType.HARD);



        return monsterModel;
    }
}
