package org.delivery.common.api

import org.delivery.common.error.ErrorCode
import org.delivery.common.error.errorCodeIfs

data class Result (
    val resultCode: Int?= null,
    val resultMessage: String?= null,
    val resultDescription: String?= null
){
    //java의 static
    companion object {
        @JvmStatic
        fun ok(): Result {
            return Result(
                resultCode = ErrorCode.OK.getErrorCode(),
                resultMessage = ErrorCode.OK.getDescription(),
                resultDescription = "성공"
            )
        }

        @JvmStatic
        fun ERROR(errorCodeIfs: errorCodeIfs): Result{
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = "에러발생"
            )
        }

        @JvmStatic
        fun ERROR(errorCodeIfs: errorCodeIfs, tw:Throwable): Result{
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = tw.localizedMessage
            )
        }

        @JvmStatic
        fun ERROR(errorCodeIfs: errorCodeIfs, description: String): Result{
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = description
            )
        }
    }
}