package com.design.rpg.model;


import com.design.rpg.model.command.HumanATKCommand;


/**
 * Created by liying on 2019/3/29.
 */
public class SoldierHumanModel extends HumanModel {


    @Override
    public void attack(MonsterModel monsterModel, HumanATKCommand humanATKCommand) {
        humanATKCommand.calculateATK(this,monsterModel);
    }

    @Override
    public void levelUp(int exp) {
        this.setLevel(this.getLevel()+1);
        this.setCurLevelExp(exp);
        //战士加3点攻击和3点耐力和3点体质,其余加1
        this.setSpirit(this.getSpirit()+1);
        this.setPhysique(this.getPhysique()+3);
        this.setAgility(this.getAgility()+1);
        this.setPower(this.getPower()+3);
        this.setEndurance(this.getEndurance()+3);
        this.setLuck(this.getLuck()+1);
        this.setMaxHP(this.getMaxHP()*this.getLevel()+this.getEndurance()*10+this.getRole().getHP());
    }
}
