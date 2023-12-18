package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.errorCodeIfs;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> ok(T data){
        var api = new Api<T>();
        api.result = Result.ok();
        api.body = data;
        return api;
    }

    public static Api<Object> ERROR(Result result){
        var api = new Api<Object>();
        api.result = result;
        return api;
    }

    public static Api<Object> ERROR(errorCodeIfs errorCodeIfs){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs);
        return api;
    }

    public static Api<Object> ERROR(errorCodeIfs errorCodeIfs, String tw){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs, tw);
        return api;
    }

}
