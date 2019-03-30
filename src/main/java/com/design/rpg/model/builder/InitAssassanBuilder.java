package com.design.rpg.model.builder;

import com.design.rpg.form.HumanType;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.AssassanHumanModel;
import com.design.rpg.model.command.NormalATKCommand;
import com.design.rpg.model.command.AssassanFirstSkillATKCommand;
import com.design.rpg.model.command.AssassanSecondSkillATKCommand;
import com.design.rpg.model.command.AssassanThirdSkillATKCommand;

/**
 * Created by liying on 2019/3/31.
 */
public class InitAssassanBuilder implements HumanBuilder {
    AssassanHumanModel assassanHumanModel;
    InitRoleBuilder initRoleBuilder=new InitRoleBuilder();


    @Override
    public void buildAttributes() {
        assassanHumanModel.setLevel(1);
        assassanHumanModel.setCurLevelExp(0);
        assassanHumanModel.setPower(2);
        assassanHumanModel.setAgility(4);
        assassanHumanModel.setSpirit(2);
        assassanHumanModel.setPhysique(2);
        assassanHumanModel.setEndurance(2);
        assassanHumanModel.setLuck(2);
        assassanHumanModel.setMoney(0);
        assassanHumanModel.setMaxHP(100);
        assassanHumanModel.setHP(100);
        assassanHumanModel.setHumanType(HumanType.SOLDIER);

    }

    @Override
    public void buildRole() {
        assassanHumanModel.setRole(initRoleBuilder.createRole());
    }

    @Override
    public void buildCDMap() {
        assassanHumanModel.getCommandList().add(new AssassanFirstSkillATKCommand());
        assassanHumanModel.getCommandList().add(new AssassanSecondSkillATKCommand());
        assassanHumanModel.getCommandList().add(new AssassanThirdSkillATKCommand());
        assassanHumanModel.getCommandList().add(new NormalATKCommand());

        assassanHumanModel.getMyCDMap().put(AssassanFirstSkillATKCommand.class,0);
        assassanHumanModel.getMyCDMap().put(AssassanSecondSkillATKCommand.class,0);
        assassanHumanModel.getMyCDMap().put(AssassanThirdSkillATKCommand.class,0);
    }

    @Override
    public HumanModel createHuman() {
        assassanHumanModel =new AssassanHumanModel();
        buildAttributes();
        buildCDMap();
        buildRole();
        return assassanHumanModel;
    }
}
