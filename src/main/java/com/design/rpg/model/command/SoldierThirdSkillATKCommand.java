package com.design.rpg.model.command;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

import java.util.Map;

/**
 * Created by liying on 2019/3/28.
 */
public class SoldierThirdSkillATKCommand implements HumanATKCommand {

    private static final int FIRST_CD=4;
    private static final int SECOND_CD=5;

    /**
     * 部分魔法攻击加轻微治疗(吸血)
     * @param humanModel
     * @param monsterModel
     * @return
     */
    @Override
    public void calculateATK(HumanModel humanModel, MonsterModel monsterModel) {

        Map<Class,Integer> cdMap=humanModel.getMyCDMap();
        //若还在cd
        if(cdMap.get(this.getClass())>0){return;}

        //攻击
        int attack=(int)(humanModel.getAtk()*1.5)+humanModel.getPower()*3;
        int defence=monsterModel.getDef();
        int hurt=attack>defence?attack-defence:0;
        monsterModel.setHp(monsterModel.getHp()-hurt);

        //当前cd设为cd值
        cdMap.replace(this.getClass(),this.getCD()+1);
        cdMap.replace(DoctorFirstSkillATKCommand.class,FIRST_CD);
        cdMap.replace(DoctorSecondSkillATKCommand.class,SECOND_CD);
        //其余cd都-1
        cdMap.replaceAll((k,v)->v==0?v:v-1);

    }

    @Override
    public String getName() {
        return "达拉崩吧之斩";
    }

    @Override
    public String getDescription() {
        return "对怪物造成爆炸性伤害";
    }

    @Override
    public int getCD() {
        return 6;
    }

    @Override
    public char getBindKey() {
        return 'L';
    }
}
