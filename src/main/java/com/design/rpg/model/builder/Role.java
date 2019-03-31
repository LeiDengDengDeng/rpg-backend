package com.design.rpg.model.builder;

import lombok.Data;


/**
 * @author deng
 * @date 2019/03/16
 */
@Data
public class Role {
    private Head head;
    private Body body;
    private Weapon weapon;

    public int getAtk(){

        return (head==null?0:head.getAtk())+(body==null?0:body.getAtk())+(weapon==null?0:weapon.getAtk());
    }
    public int getDef(){
        return (head==null?0:head.getDef())+(body==null?0:body.getDef())+(weapon==null?0:weapon.getDef());
    }

    public int getHp(){
        return (head==null?0:head.getHp())+(body==null?0:body.getHp())+(weapon==null?0:weapon.getHp());
    }

}
