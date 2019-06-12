package studio.rollinrock.cnunicom.jphospital.controller;

import lombok.Data;
import lombok.NonNull;

@Data
public class HttpResult<E> {
    private E data;
    private boolean requestSuccessful;
    private String resultCode;
    private String resultDesc;

    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_DESC = "success";
    public static final String FAILURE_CODE = "-1";
    public static final String FAILURE_DESC = "failure";

    public static <T> HttpResult<T> succeedWithData(@NonNull T data){
        HttpResult<T> result = new HttpResult<>();
        result.requestSuccessful = true;
        result.resultCode = SUCCESS_CODE;
        result.resultDesc = SUCCESS_DESC;
        result.data = data;

        return result;
    }

    public static <T> HttpResult<T> failWithReason(String reason){
        HttpResult<T> result = new HttpResult<>();
        result.requestSuccessful = false;
        result.resultCode = FAILURE_CODE;
        result.resultDesc = reason;
        result.data = null;

        return result;
    }
}
