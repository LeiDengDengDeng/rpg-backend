package com.design.rpg.vo;

import com.design.rpg.form.HumanType;
import lombok.Data;

/**
 * @author deng
 * @date 2019/03/31
 */
@Data
public class WeaponEquipmentVO extends EquipmentVO {
    private HumanType humanType;
}
