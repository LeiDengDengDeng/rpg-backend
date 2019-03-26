package com.design.rpg.model.builder;

/**
 * @author deng
 * @date 2019/03/16
 */
public interface RoleBuilder {
    void buildHead();

    void buildBody();

    void buildWeapon();

    Role createRole();
}
