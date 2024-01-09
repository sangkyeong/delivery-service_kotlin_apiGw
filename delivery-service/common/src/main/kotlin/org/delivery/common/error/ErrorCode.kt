package org.delivery.common.error

enum class ErrorCode(
    private val httpStatusCode: Int,
    private val errorCode: Int,
    private val description: String
): errorCodeIfs {
    OK(200, 200, "성공"),
    BAD_REQUEST(400, 400, "잘못된요청"),

    SERVER_ERROR(500, 500, "서버에러"),

    NULL_POINT(500, 512, "NULL POINT")
    ;

    override fun getHttpStatusCode(): Int {
        return this.httpStatusCode
    }

    override fun getErrorCode(): Int {
        return this.errorCode
    }

    override fun getDescription(): String {
        return this.description
    }
}