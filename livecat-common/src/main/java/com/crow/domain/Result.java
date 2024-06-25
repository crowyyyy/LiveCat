package com.crow.domain;

public class Result<T> {
    private boolean success;
    private T data;
    private String errMsg;
    private String errCode;


    public static <K> Result<K> success(K data) {
        Result<K> result = new Result<>();
        result.success = Boolean.TRUE;
        result.data = data;
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static <K> Result<K> newErrorResult(String errCode, String errMsg) {
        Result<K> errorResult = new Result<>();
        errorResult.success = Boolean.FALSE;
        errorResult.errCode = errCode;
        errorResult.errMsg = errMsg;
        return errorResult;
    }
}
