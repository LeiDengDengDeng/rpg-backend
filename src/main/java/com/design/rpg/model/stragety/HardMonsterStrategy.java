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
public class HardMonsterStrategy implements MonsterGenerateStrategy{
    @Override
    public MonsterModel createMonster(HumanModel humanModel) {
        MonsterModel monsterModel=new MonsterModel();
        monsterModel.setHp((int)(humanModel.getMaxHP()*1.2));
        monsterModel.setMaxHP((int)(humanModel.getMaxHP()*1.2));
        monsterModel.setAtk((int)(humanModel.getDef()*1.8));
        monsterModel.setDef((int)(humanModel.getAtk()*0.9));
        monsterModel.setWinMoney((int)(13+Math.random()*9));
        monsterModel.setWinExp((int)(13+Math.random()*9));
        monsterModel.setMonsterType(MonsterType.HARD);
        int equipmentNum=1+(int)(Math.random()*3);
        List<Equipment> winEquipment=new ArrayList<>();
        for (int i = 0; i < equipmentNum; i++) {
            winEquipment.add(EquipmentFactory.createEquipmentByRandom(ColorType.PURPLE));
        }
        monsterModel.setWinEquipment(winEquipment);



        return monsterModel;
    }
}
