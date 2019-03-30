package com.design.rpg.model;


import com.design.rpg.form.HumanType;
import com.design.rpg.model.builder.InitRoleBuilder;
import com.design.rpg.model.builder.RoleBuilder;
import com.design.rpg.model.command.*;


/**
 * Created by liying on 2019/3/29.
 */
public class DoctorHumanModel extends HumanModel {


    @Override
    public void attack(MonsterModel monsterModel, HumanATKCommand humanATKCommand) {
        humanATKCommand.calculateATK(this,monsterModel);
    }

    @Override
    public void levelUp(int exp) {
        this.setLevel(this.getLevel()+1);
        this.setCurLevelExp(exp);
        //牧师加五点攻击和五点体质,其余加1
        this.setSpirit(this.getSpirit()+5);
        this.setPhysique(this.getPhysique()+5);
        this.setAgility(this.getAgility()+1);
        this.setPower(this.getPower()+1);
        this.setEndurance(this.getEndurance()+1);
        this.setLuck(this.getLuck()+1);
        this.setMaxHP(this.getMaxHP()*this.getLevel()+this.getEndurance()*10+this.getRole().getHP());
    }
}
