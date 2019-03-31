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
public class NormalMonsterStrategy implements MonsterGenerateStrategy{
    @Override
    public MonsterModel createMonster(HumanModel humanModel) {
        MonsterModel monsterModel=new MonsterModel();
        monsterModel.setHp((int)(humanModel.getMaxHP()*0.8));
        monsterModel.setMaxHP((int)(humanModel.getMaxHP()*0.8));
        monsterModel.setAtk((int)(humanModel.getDef()*1.4));
        monsterModel.setDef((int)(humanModel.getAtk()*0.6));
        //6-12
        monsterModel.setWinMoney((int)(6+Math.random()*7));
        monsterModel.setWinExp((int)(6+Math.random()*7));
        monsterModel.setMonsterType(MonsterType.NORMAL);
        int equipmentNum=1+(int)(Math.random()*3);
        List<Equipment> winEquipment=new ArrayList<>();
        for (int i = 0; i < equipmentNum; i++) {
            winEquipment.add(EquipmentFactory.createEquipmentByRandom(ColorType.BLUE));
        }
        monsterModel.setWinEquipment(winEquipment);
        return monsterModel;
    }
}
