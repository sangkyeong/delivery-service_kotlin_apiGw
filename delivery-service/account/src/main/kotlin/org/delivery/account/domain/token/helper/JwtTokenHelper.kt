package org.delivery.account.domain.token.helper

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.delivery.account.domain.token.ifs.TokenHelper
import org.delivery.account.domain.token.model.TokenDto
import org.delivery.common.error.TokenErrorCode
import org.delivery.common.exception.ApiException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.crypto.SecretKey
import kotlin.collections.HashMap

@Component
class JwtTokenHelper: TokenHelper {

    @Value("\${token.secret.key}")
    private val secretKey: String? = null

    @Value("\${token.access-token.plus-hour}")
    private val accessTokenPlusHour: Long= 1

    @Value("\${token.refresh-token.plus-hour}")
    private val refreshTokenPlusHour: Long= 12

    private fun getSigningKey(): SecretKey {
        val keyBytes = secretKey!!.toByteArray()
        return Keys.hmacShaKeyFor(keyBytes)
    }

    override fun issueAccessToken(data: Map<String, Any?>): TokenDto? {
       val expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour)

        val expiredAt = Date.from(expiredLocalDateTime.atZone(
            ZoneId.systemDefault()
        ).toInstant())

        //val key = Keys.hmacShaKeyFor(secretKey?.toByteArray())

        val jwtToken = Jwts.builder()
            .signWith(getSigningKey())
            .claims(data)
            .expiration(expiredAt)
            .compact()

        return TokenDto(
            token = jwtToken,
            expiredAt = expiredLocalDateTime
        )
    }

    override fun issueRefreshToken(data: Map<String, Any?>): TokenDto? {
        val expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour)

        val expiredAt = Date.from(expiredLocalDateTime.atZone(
            ZoneId.systemDefault()
        ).toInstant())

        //val key = Keys.hmacShaKeyFor(secretKey?.toByteArray())

        val jwtToken = Jwts.builder()
            .signWith(getSigningKey())
            .claims(data)
            .expiration(expiredAt)
            .compact()

        return TokenDto(
            token = jwtToken,
            expiredAt = expiredLocalDateTime
        )
    }

    override fun validationTokenWithThrow(token: String?): Map<String, Any>? {
        val parser = Jwts.parser()
            .verifyWith(getSigningKey())
            .build()

        return try {
            val result = token?.let { parser.parseEncryptedClaims(it) }
            HashMap(result!!.payload)
        }catch (e: Exception){
            when(e){

                //토큰이 유효하지않을때
                is SignatureException -> {
                    throw  ApiException(TokenErrorCode.INVALID_TOKEN, e)
                }

                //만료토큰
                is ExpiredJwtException ->{
                    throw  ApiException(TokenErrorCode.EXPIRED_TOKEN, e)
                }

                //그 외에러
                else -> {
                    throw  ApiException(TokenErrorCode.TOKEN_EXCEPTION, e)
                }
            }
        }
    }
}