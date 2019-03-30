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

    public int getATK(){

        return (head==null?0:head.getATK())+(body==null?0:body.getATK())+(weapon==null?0:weapon.getATK());
    }
    public int getDEF(){
        return (head==null?0:head.getDEF())+(body==null?0:body.getDEF())+(weapon==null?0:weapon.getDEF());
    }

    public int getHP(){
        return (head==null?0:head.getHP())+(body==null?0:body.getHP())+(weapon==null?0:weapon.getHP());
    }

}
