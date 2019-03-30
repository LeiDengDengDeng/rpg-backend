package com.design.rpg.vo;

import lombok.Data;

/**
 * @author deng
 * @date 2019/03/31
 */
@Data
public class SkillVO {
    private String name;
    private String description;
    private int cd;
    private int remainCd;
    private char key;
}
