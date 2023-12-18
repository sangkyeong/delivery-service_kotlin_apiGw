package org.delivery.api.common.exception;

import org.delivery.api.common.error.errorCodeIfs;

public interface ApiExceptionIfs {
    errorCodeIfs getErrorCodeIfs();

    String getErrorDescription();
}
