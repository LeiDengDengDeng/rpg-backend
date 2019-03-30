package com.design.rpg.model;


import com.design.rpg.form.HumanType;
import com.design.rpg.model.command.*;


/**
 * Created by liying on 2019/3/29.
 */
public class DoctorHumanModel extends HumanModel {

    private static final int FIRST_SKILL_CD=4;
    private static final int SECOND_SKILL_CD=3;
    private static final int THIRD_SKILL_CD=2;


    @Override
    public void init() {
        this.getCdMap().put(DoctorFirstSkillATKCommand.class,FIRST_SKILL_CD);
        this.getCdMap().put(DoctorSecondSkillATKCommand.class,SECOND_SKILL_CD);
        this.getCdMap().put(DoctorThirdSkillATKCommand.class,THIRD_SKILL_CD);
        this.getCdMap().put(NormalATKCommand.class,0);

        this.getMyCDMap().put(DoctorFirstSkillATKCommand.class,0);
        this.getMyCDMap().put(DoctorSecondSkillATKCommand.class,0);
        this.getMyCDMap().put(DoctorThirdSkillATKCommand.class,0);
        this.setLevel(1);
        this.setCurLevelExp(0);
        this.setPower(2);
        this.setAgility(2);
        this.setSpirit(4);
        this.setPhysique(2);
        this.setEndurance(2);
        this.setLuck(2);
        this.setMoney(0);
        this.setMaxHP(100);
        this.setHP(100);
        this.setHumanType(HumanType.DOCTOR);

    }

    @Override
    public void attack(MonsterModel monsterModel, HumanATKCommand humanATKCommand) {
        humanATKCommand.calculateATK(this,monsterModel);
    }

    @Override
    public void levelUp(int exp) {
        this.setLevel(this.getLevel()+1);
        this.setCurLevelExp(exp);
        //牧师加五点攻击和五点体质
        this.setSpirit(this.getSpirit()+5);
        this.setPhysique(this.getPhysique()+5);
        this.setMaxHP(this.getMaxHP()*this.getLevel()+this.getEndurance()*10);
    }
}
