package org.delivery.common.error

interface errorCodeIfs {
    fun getHttpStatusCode(): Int
    fun getErrorCode(): Int
    fun getDescription(): String
}