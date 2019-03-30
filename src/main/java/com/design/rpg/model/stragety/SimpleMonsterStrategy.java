package com.design.rpg.model.stragety;

import com.design.rpg.form.ColorType;
import com.design.rpg.form.MonsterType;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;
import com.design.rpg.model.builder.Equipment;
import com.design.rpg.model.factory.EquipmentFactory;

import java.util.ArrayList;
import java.util.List;

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
        int equipmentNum=1+(int)(Math.random()*3);
        List<Equipment> winEquipment=new ArrayList<>();
        for (int i = 0; i < equipmentNum; i++) {
            winEquipment.add(EquipmentFactory.createEquipmentByRandom(ColorType.GREEN));
        }
        monsterModel.setWinEquipment(winEquipment);
        return monsterModel;
    }
}
