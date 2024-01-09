package org.delivery.common.api

import jakarta.validation.Valid
import org.delivery.common.error.errorCodeIfs

data class Api<T> (
    var result: Result?=null,

    @field: Valid
    var body: T?=null
){
 companion object{

     @JvmStatic
     fun <T> ok(body: T?): Api<T>{
         return Api(
             result = Result.ok(),
             body = body
         )
     }

     @JvmStatic
     fun <T> ERROR(result: Result): Api<T>{
         return Api(
             result = result
         )
     }

     @JvmStatic
     fun <T> ERROR(errorCodeIfs: errorCodeIfs): Api<T>{
         return Api(
             result = Result.ERROR(errorCodeIfs)
         )
     }

     @JvmStatic
     fun <T> ERROR(errorCodeIfs: errorCodeIfs, tw: String): Api<T>{
         return Api(
             result = Result.ERROR(errorCodeIfs, tw)
         )
     }
 }
}
