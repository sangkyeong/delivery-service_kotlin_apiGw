package org.delivery.account.domain.token.service

import org.delivery.account.domain.token.ifs.TokenHelper
import org.delivery.account.domain.token.model.TokenDto
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val tokenHelper: TokenHelper
) {

    fun issueAccessToken(userId: Long?)
    : TokenDto?{
        return userId?.let {
            val data = mapOf("userId" to it)
            tokenHelper.issueAccessToken(data)
        }
    }

    fun issueRefreshToken(userId: Long?)
    : TokenDto?{
        requireNotNull(userId)
        val data = mapOf("userId" to userId)
        return tokenHelper.issueRefreshToken(data)
    }

    fun validationToken(token: String)
    : Long?{
        /*
        requireNotNull(token)

        val map = tokenHelper.validationTokenWithThrow(token)

        val userId = map?.get("userId")

        requireNotNull(userId)

        return userId.toString().toLong()
        */

        //이렇게 처리도 가능
        return token?.let { token ->
            tokenHelper.validationTokenWithThrow(token)
            ?.let { map ->
                map["userId"]
            }?.let { userId ->
                userId.toString().toLong()
            }
        }
    }

}