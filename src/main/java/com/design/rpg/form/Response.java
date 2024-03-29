package com.design.rpg.form;

import com.design.rpg.exception.ServiceException;
import lombok.Data;

/**
 * @author deng
 * @date 2018/11/7
 */
@Data
public class Response<T> {
    /**
     * 数据
     */
    private T res;
    /**
     * 状态码
     */
    private int code;

    public Response(int code, T res) {
        this.res = res;
        this.code = code;
    }

    public static <T> Response<T> okEmptyResponse() {
        return okResponse(null);
    }

    public static <T> Response<T> okResponse(Object data) {
        return new Response(0, data);
    }

    public static Response<String> errResponse(ServiceException e) {
        return new Response(e.getErrCode(), e.getMessage());
    }
}
