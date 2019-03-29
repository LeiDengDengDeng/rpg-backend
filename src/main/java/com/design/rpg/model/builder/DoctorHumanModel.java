package com.design.rpg.model.builder;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;
import com.design.rpg.model.strategy.HumanATKStrategy;

/**
 * Created by liying on 2019/3/29.
 */
public class DoctorHumanModel extends HumanModel {

    @Override
    public void attack(MonsterModel monsterModel, HumanATKStrategy humanATKStrategy) {

    }

    @Override
    public void levelUp(int exp) {
        this.setLevel(this.getLevel()+1);
        this.setCurLevelExp(exp);
        //todo 设置各个属性的增量

    }
}
