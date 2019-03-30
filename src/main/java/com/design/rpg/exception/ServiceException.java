package com.design.rpg.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author deng
 * @date 2018/11/7
 */
@Data
public class ServiceException extends RuntimeException {
    private int errCode;
    private String message;


    public static final int CAN_NOT_OPERATE = 600;
    public static final int IN_CD = 601;
    public static final int NOT_EXIST = 602;
    public static final int NOT_MATCHED = 603;
    public static final int HUMAN_WRONG_TYPE = 604;
    public static final int MONEY_NOT_ENOUGH = 605;



    private static Map<Integer, String> errorMap;

    static {
        errorMap = new HashMap<>();
        errorMap.put(CAN_NOT_OPERATE, "无法操作");
        errorMap.put(IN_CD, "技能CD中");
        errorMap.put(NOT_EXIST, "角色不存在");
        errorMap.put(NOT_MATCHED,"装备和角色不匹配");
        errorMap.put(HUMAN_WRONG_TYPE, "角色类型异常");
        errorMap.put(HUMAN_WRONG_TYPE,"角色金币不够");
    }

    public ServiceException(int errCode) {
        this.errCode = errCode;
        this.message = errorMap.getOrDefault(errCode, "未知错误");
    }

    public ServiceException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
        this.message = message;
    }

}