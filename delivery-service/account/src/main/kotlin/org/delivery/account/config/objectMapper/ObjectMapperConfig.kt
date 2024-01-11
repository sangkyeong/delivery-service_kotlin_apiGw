package org.delivery.account.config.objectMapper

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper{

        //kotlin module
        val kotlinModule = KotlinModule.Builder().apply {
            withReflectionCacheSize(512)
            configure(KotlinFeature.NullToEmptyCollection, false)   //list false시 default는 size가 0으로 만들어짐
                                                                            //true시 list가 만들어짐
            configure(KotlinFeature.NullToEmptyMap, false)          //map
            configure(KotlinFeature.NullIsSameAsDefault, false)
            configure(KotlinFeature.SingletonSupport, false)
            configure(KotlinFeature.StrictNullChecks, false)        //null검사 끄기
        }.build()

        val objectMapper = ObjectMapper().apply {
            registerModule(Jdk8Module())
            registerModule(JavaTimeModule())
            registerModule(kotlinModule)

            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)

            disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)

            propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE

        }

        return objectMapper
    }
}