package com.design.rpg.util;

import com.design.rpg.exception.GlobalExceptionHandler;
import com.design.rpg.exception.ServiceException;

import java.util.Objects;

/**
 * 在Service层进行断言判断
 * 如果判断失败
 * 则抛出异常，并在Controller层由<tt>ControllerAdvice</tt>捕获。
 * <tt>ControllerAdvice</tt>的配置位于{@link GlobalExceptionHandler}类中
 *
 * @see GlobalExceptionHandler
 */
public class AssertUtil {
    /**
     * 判断条件是否为真，若为否，则抛出{@link ServiceException}异常
     *
     * @param bool         条件
     * @param errCode      错误码
     * @throws ServiceException
     */
    public static void assertTrue(boolean bool, int errCode) {
        if (!bool) {
            throw new ServiceException(errCode);
        }
    }

    /**
     * 判断对象是否非空，如果为Null，抛出{@link ServiceException}异常
     *
     * @param entity       对象
     * @param errCode      错误码
     * @throws ServiceException
     */
    public static void assertNotNull(Object entity, int errCode) {
        if (Objects.isNull(entity)) {
            throw new ServiceException(errCode);
        }
    }
}
