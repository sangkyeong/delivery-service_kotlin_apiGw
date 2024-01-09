package org.delivery.common.exception

import org.delivery.common.error.errorCodeIfs

interface ApiExceptionIfs {
    val errorIfs: errorCodeIfs?
    val errorDescription: String?
}