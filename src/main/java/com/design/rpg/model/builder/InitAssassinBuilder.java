package com.design.rpg.model.builder;

import com.design.rpg.form.HumanType;
import com.design.rpg.model.AssassinHumanModel;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.command.AssassanFirstSkillATKCommand;
import com.design.rpg.model.command.AssassanSecondSkillATKCommand;
import com.design.rpg.model.command.AssassanThirdSkillATKCommand;
import com.design.rpg.model.command.NormalATKCommand;

/**
 * Created by liying on 2019/3/31.
 */
public class InitAssassinBuilder implements HumanBuilder {
    AssassinHumanModel assassinHumanModel;
    InitRoleBuilder initRoleBuilder=new InitRoleBuilder();


    @Override
    public void buildAttributes() {
        assassinHumanModel.setLevel(1);
        assassinHumanModel.setCurLevelExp(0);
        assassinHumanModel.setPower(2);
        assassinHumanModel.setAgility(4);
        assassinHumanModel.setSpirit(2);
        assassinHumanModel.setPhysique(2);
        assassinHumanModel.setEndurance(2);
        assassinHumanModel.setLuck(2);
        assassinHumanModel.setMoney(0);
        assassinHumanModel.setMaxHP(100);
        assassinHumanModel.setHp(100);
        assassinHumanModel.setHumanType(HumanType.ASSASSIN);

    }

    @Override
    public void buildRole() {
        assassinHumanModel.setRole(initRoleBuilder.createRole());
    }

    @Override
    public void buildCDMap() {
        assassinHumanModel.getCommandList().add(new AssassanFirstSkillATKCommand());
        assassinHumanModel.getCommandList().add(new AssassanSecondSkillATKCommand());
        assassinHumanModel.getCommandList().add(new AssassanThirdSkillATKCommand());
        assassinHumanModel.getCommandList().add(new NormalATKCommand());

        assassinHumanModel.getMyCDMap().put(AssassanFirstSkillATKCommand.class,0);
        assassinHumanModel.getMyCDMap().put(AssassanSecondSkillATKCommand.class,0);
        assassinHumanModel.getMyCDMap().put(AssassanThirdSkillATKCommand.class,0);
    }

    @Override
    public HumanModel createHuman() {
        assassinHumanModel =new AssassinHumanModel();
        buildAttributes();
        buildCDMap();
        buildRole();
        return assassinHumanModel;
    }
}
