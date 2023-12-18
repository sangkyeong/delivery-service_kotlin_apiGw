package org.delivery.api.common.exception;

import lombok.Getter;
import org.delivery.api.common.error.errorCodeIfs;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs{

    private final errorCodeIfs errorCodeIfs;

    private final String errorDescription;

    public ApiException(errorCodeIfs errorCodeIfs){
        super(errorCodeIfs.getDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public ApiException(errorCodeIfs errorCodeIfs, String description){
        super(description);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = description;
    }

    public ApiException(errorCodeIfs errorCodeIfs, Throwable tw){
        super(tw);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public ApiException(errorCodeIfs errorCodeIfs, Throwable tw, String errorDescription){
        super(tw);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }
}
