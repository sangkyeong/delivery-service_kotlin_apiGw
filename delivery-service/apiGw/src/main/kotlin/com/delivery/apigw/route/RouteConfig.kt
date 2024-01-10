package com.delivery.apigw.route

import com.delivery.apigw.filter.ServicePrivateFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(
    private val servicePrivateFilter: ServicePrivateFilter
) {

    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {

        //application.yaml 말고 여기다 지정해도 됨
        //설정사항이 많거나 복잡한 경우 코드 생성추천
        return builder.routes()
            .route{spec ->
                spec.order(-1)  //우선순위
                spec.path(
                    "/service-api/api/**"   //매칭할 주소
                ).filters{filterSpec ->
                    filterSpec.filter(servicePrivateFilter.apply(ServicePrivateFilter.Config()))    //필터지정
                    filterSpec.rewritePath("/service-api(?<segment>/?.*)", "\${segment}")
                }.uri(
                    "http://localhost:8080" //라우팅할 주소
                )
            }.build()
    }
}