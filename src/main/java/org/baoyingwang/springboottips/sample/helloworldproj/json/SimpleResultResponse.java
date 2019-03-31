package org.baoyingwang.springboottips.sample.helloworldproj.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimpleResultResponse<T> {

    public static <T> SimpleResultResponse<T> newInstance(T data){

        SimpleResultResponse result = new SimpleResultResponse<T>();
        result.setData(data);
        return result;
    }

    public static <T> SimpleResultResponse<T> newError(int errorCode, String errorMessage){
        SimpleResultResponse result = new SimpleResultResponse<T>();
        result.setErrorCode(errorCode);
        result.setErrorMessage(errorMessage);
        return result;

    }

    @JsonProperty("error-code")
    private Integer errorCode;

    @JsonProperty("error-message")
    private String errorMessage;

    @JsonProperty("data")
    private T data;
}
