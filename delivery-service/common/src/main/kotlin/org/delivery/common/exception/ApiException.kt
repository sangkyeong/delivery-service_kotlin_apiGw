package org.delivery.common.exception

import org.delivery.common.error.errorCodeIfs

class ApiException : RuntimeException, ApiExceptionIfs {
    override val errorDescription: String
    override val errorIfs: errorCodeIfs

    constructor(errorCodeIfs: errorCodeIfs): super(errorCodeIfs.getDescription()){
        this.errorIfs = errorCodeIfs
        this.errorDescription = errorCodeIfs.getDescription()
    }

    constructor(
        errorCodeIfs: errorCodeIfs,
        errorDescription: String
    ): super(errorDescription){
        this.errorIfs = errorCodeIfs
        this.errorDescription = errorDescription
    }

    constructor(
        errorCodeIfs: errorCodeIfs,
        tw: Throwable
    ): super(tw){
        this.errorIfs = errorCodeIfs
        this.errorDescription = errorCodeIfs.getDescription()
    }

    constructor(
        errorCodeIfs: errorCodeIfs,
        tw: Throwable,
        errorDescription: String
    ): super(tw){
        this.errorIfs = errorCodeIfs
        this.errorDescription = errorDescription
    }
}