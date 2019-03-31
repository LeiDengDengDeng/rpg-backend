package com.design.rpg.model.builder;

import com.design.rpg.form.HumanType;
import com.design.rpg.model.DoctorHumanModel;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.command.DoctorFirstSkillATKCommand;
import com.design.rpg.model.command.DoctorSecondSkillATKCommand;
import com.design.rpg.model.command.DoctorThirdSkillATKCommand;
import com.design.rpg.model.command.NormalATKCommand;

/**
 * Created by liying on 2019/3/31.
 */
public class InitDoctorBuilder implements HumanBuilder {
    DoctorHumanModel doctorHumanModel;
    InitRoleBuilder initRoleBuilder=new InitRoleBuilder();


    @Override
    public void buildAttributes() {
        doctorHumanModel.setLevel(1);
        doctorHumanModel.setCurLevelExp(0);
        doctorHumanModel.setPower(2);
        doctorHumanModel.setAgility(2);
        doctorHumanModel.setSpirit(4);
        doctorHumanModel.setPhysique(2);
        doctorHumanModel.setEndurance(2);
        doctorHumanModel.setLuck(2);
        doctorHumanModel.setMoney(0);
        doctorHumanModel.setMaxHP(100);
        doctorHumanModel.setHp(100);
        doctorHumanModel.setHumanType(HumanType.DOCTOR);

    }

    @Override
    public void buildRole() {
        doctorHumanModel.setRole(initRoleBuilder.createRole());
    }

    @Override
    public void buildCDMap() {
        doctorHumanModel.getCommandList().add(new DoctorFirstSkillATKCommand());
        doctorHumanModel.getCommandList().add(new DoctorSecondSkillATKCommand());
        doctorHumanModel.getCommandList().add(new DoctorThirdSkillATKCommand());
        doctorHumanModel.getCommandList().add(new NormalATKCommand());

        doctorHumanModel.getMyCDMap().put(DoctorFirstSkillATKCommand.class,0);
        doctorHumanModel.getMyCDMap().put(DoctorSecondSkillATKCommand.class,0);
        doctorHumanModel.getMyCDMap().put(DoctorThirdSkillATKCommand.class,0);
    }

    @Override
    public HumanModel createHuman() {
        doctorHumanModel =new DoctorHumanModel();
        buildAttributes();
        buildCDMap();
        buildRole();
        return doctorHumanModel;
    }
}
