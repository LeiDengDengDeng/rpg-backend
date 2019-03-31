package com.design.rpg.model;


import com.design.rpg.model.command.HumanATKCommand;


/**
 * Created by liying on 2019/3/29.
 */
public class AssassinHumanModel extends HumanModel {


    @Override
    public void attack(MonsterModel monsterModel, HumanATKCommand humanATKCommand) {
        humanATKCommand.calculateATK(this,monsterModel);
    }

    @Override
    public void levelUp(int exp) {
        this.setLevel(this.getLevel()+1);
        this.setCurLevelExp(exp);
        //刺客加9点敏捷,其余加1
        this.setSpirit(this.getSpirit()+1);
        this.setPhysique(this.getPhysique()+1);
        this.setAgility(this.getAgility()+9);
        this.setPower(this.getPower()+1);
        this.setEndurance(this.getEndurance()+1);
        this.setLuck(this.getLuck()+1);
        this.setMaxHP(this.getMaxHP()*this.getLevel()+this.getEndurance()*10+this.getRole().getHP());
    }
}
