package com.design.rpg.vo.factory;

import com.design.rpg.form.EquipmentType;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.builder.Equipment;
import com.design.rpg.model.command.HumanATKCommand;
import com.design.rpg.util.Converter;
import com.design.rpg.vo.EquipmentVO;
import com.design.rpg.vo.HumanVO;
import com.design.rpg.vo.SkillVO;
import com.design.rpg.vo.WeaponEquipmentVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deng
 * @date 2019/03/31
 */
public final class HumanVOFactory {
    private HumanVOFactory() {
    }

    public static HumanVO createHumanVO(HumanModel humanModel) {
        HumanVO humanVO = (HumanVO) Converter.map(humanModel, HumanVO.class);
        humanVO.setCurLevelMaxExp(humanModel.getLevelExp());

        List<SkillVO> skills = new ArrayList<>();
        for (HumanATKCommand skill : humanModel.getCommandList()) {
            SkillVO skillVO = new SkillVO();
            skillVO.setName(skill.getName());
            skillVO.setDescription(skill.getDescription());
            skillVO.setKey(skill.getBindKey());
            skillVO.setRemainCd(humanModel.getMyCDMap().getOrDefault(skill.getClass(), 0));
            skillVO.setCd(skill.getCD());

            skills.add(skillVO);
        }
        humanVO.setSkills(skills);

        List<EquipmentVO> equipments = new ArrayList<>();
        for (Equipment equipment : humanModel.getBag()) {
            EquipmentVO equipmentVO = equipment.getType() == EquipmentType.WEAPON ? (WeaponEquipmentVO) Converter.map(equipment, WeaponEquipmentVO.class) :
                    (EquipmentVO) Converter.map(equipment, EquipmentVO.class);

            equipmentVO.setSplitMoney(equipment.splitUp());
            equipmentVO.setStrengthMoney(equipment.getMoneyPayed());

            equipments.add(equipmentVO);
        }
        humanVO.setBag(equipments);

        return humanVO;
    }
}
