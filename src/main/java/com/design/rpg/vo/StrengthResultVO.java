package com.design.rpg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author deng
 * @date 2019/03/31
 */
@Data
@AllArgsConstructor
public class StrengthResultVO {
    private HumanVO humanModel;
    private boolean successFlag;
}
