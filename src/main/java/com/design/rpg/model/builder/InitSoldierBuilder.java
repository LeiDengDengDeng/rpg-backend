package com.design.rpg.model.builder;

import com.design.rpg.form.HumanType;
import com.design.rpg.model.SoldierHumanModel;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.command.SoldierFirstSkillATKCommand;
import com.design.rpg.model.command.SoldierSecondSkillATKCommand;
import com.design.rpg.model.command.SoldierThirdSkillATKCommand;
import com.design.rpg.model.command.NormalATKCommand;

/**
 * Created by liying on 2019/3/31.
 */
public class InitSoldierBuilder implements HumanBuilder {
    SoldierHumanModel soldierHumanModel;
    InitRoleBuilder initRoleBuilder=new InitRoleBuilder();


    @Override
    public void buildAttributes() {
        soldierHumanModel.setLevel(1);
        soldierHumanModel.setCurLevelExp(0);
        soldierHumanModel.setPower(4);
        soldierHumanModel.setAgility(2);
        soldierHumanModel.setSpirit(2);
        soldierHumanModel.setPhysique(2);
        soldierHumanModel.setEndurance(2);
        soldierHumanModel.setLuck(2);
        soldierHumanModel.setMoney(0);
        soldierHumanModel.setMaxHP(100);
        soldierHumanModel.setHP(100);
        soldierHumanModel.setHumanType(HumanType.SOLDIER);

    }

    @Override
    public void buildRole() {
        soldierHumanModel.setRole(initRoleBuilder.createRole());
    }

    @Override
    public void buildCDMap() {
        soldierHumanModel.getCommandList().add(new SoldierFirstSkillATKCommand());
        soldierHumanModel.getCommandList().add(new SoldierSecondSkillATKCommand());
        soldierHumanModel.getCommandList().add(new SoldierThirdSkillATKCommand());
        soldierHumanModel.getCommandList().add(new NormalATKCommand());

        soldierHumanModel.getMyCDMap().put(SoldierFirstSkillATKCommand.class,0);
        soldierHumanModel.getMyCDMap().put(SoldierSecondSkillATKCommand.class,0);
        soldierHumanModel.getMyCDMap().put(SoldierThirdSkillATKCommand.class,0);
    }

    @Override
    public HumanModel createHuman() {
        soldierHumanModel =new SoldierHumanModel();
        buildAttributes();
        buildCDMap();
        buildRole();
        return soldierHumanModel;
    }
}
