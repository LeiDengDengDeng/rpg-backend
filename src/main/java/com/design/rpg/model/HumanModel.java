package com.design.rpg.model;

import com.design.rpg.exception.ServiceException;
import com.design.rpg.form.HumanType;
import com.design.rpg.model.builder.*;
import com.design.rpg.model.command.HumanATKCommand;
import com.design.rpg.util.AssertUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人物
 *
 * @author deng
 * @date 2019/03/04
 */
@Data
public abstract class HumanModel extends Creature {
    private int level;
    private int curLevelExp;

    private int power;      // 力量
    private int agility;    // 敏捷
    private int spirit;     // 精神
    private int physique;   // 体质
    private int endurance;  // 耐力
    private int luck;       // 幸运
    HumanType humanType;

    List<Equipment> bag;    //背包
    Role role;              //身上的装备


    private List<HumanATKCommand> commandList;
    private Map<Class,Integer> myCDMap;

    public HumanModel(){
        commandList=new ArrayList<>();
        myCDMap=new HashMap<>();
        bag=new ArrayList<>();
    }

    // HP等的计算方式与属性有关

    // 背包
    // 身上的装备
    private int money;


    abstract public void attack(MonsterModel monsterModel, HumanATKCommand humanATKCommand);

    final public boolean expUp(int exp) {
        curLevelExp+=exp;
        //当前等级需要经验
        int levelExp=this.getLevelExp();
        int leftExp=curLevelExp-levelExp;
        if(leftExp>=0){
            levelUp(leftExp);
            return true;
        }
        return false;
    }

    abstract public void levelUp(int exp);


    public void revive() {
        this.setHp((int)(this.getMaxHP()*0.8));
    }
    @Override
    public int getAtk(){
        return (power+agility+spirit+physique+endurance)*3+role.getAtk();
    }
    @Override
    public int getDef(){
        return (endurance)*3+role.getDef();
    }

    public int getLevelExp(){
        return (int)Math.pow(level,2)*10;
    }

    public void equip(String uuid){
        Equipment equipment=bag.stream().filter(equipment1 -> equipment1.getUuid().equals(uuid)).findFirst().get();
        AssertUtil.assertNotNull(equipment,ServiceException.NOT_EXIST);
        int maxHP=this.getMaxHP();
        switch (equipment.getType()){
            case BODY:
                if(role.getBody()!=null){
                    bag.add(role.getBody());
                    maxHP-=role.getBody().getHp();
                }
                role.setBody((Body)equipment);
                break;
            case WEAPON:
                Weapon weapon=(Weapon)equipment;
                AssertUtil.assertTrue(weapon.getWeaponType()==this.getHumanType(),ServiceException.NOT_MATCHED);
                if(role.getWeapon()!=null){
                    bag.add(role.getWeapon());
                    maxHP-=role.getWeapon().getHp();
                }
                role.setWeapon(weapon);
                break;
            case HEAD:
                if(role.getHead()!=null){
                    bag.add(role.getHead());
                    maxHP-=role.getHead().getHp();
                }
                role.setHead((Head)equipment);
                break;
            default:
                break;
        }
        this.setMaxHP(maxHP+equipment.getHp());
        bag.remove(equipment);
    }

    public int splitEquipment(String uuid){
        Equipment equipment=bag.stream().filter(equipment1 -> equipment1.getUuid().equals(uuid)).findFirst().get();
        AssertUtil.assertNotNull(equipment,ServiceException.NOT_EXIST);
        int money=equipment.splitUp();
        bag.remove(equipment);
        this.setMoney(this.getMoney()+money);
        return money;
    }
    public void dead(){
        this.money=(int)(this.money*0.9);
    }

    public void getNewItems(List<Equipment> equipments){
        this.bag.addAll(equipments);
    }

    public boolean strengthEquipment(String uuid){
        Equipment equipment=bag.stream().filter(equipment1 -> equipment1.getUuid().equals(uuid)).findFirst().get();
        AssertUtil.assertNotNull(equipment,ServiceException.NOT_EXIST);
        int money=equipment.getMoneyPayed();
        AssertUtil.assertTrue(money<=this.money,ServiceException.MONEY_NOT_ENOUGH);
        boolean res=equipment.levelUp();
        if(res){
            this.money-=money;
        }
        return res;
    }

}
