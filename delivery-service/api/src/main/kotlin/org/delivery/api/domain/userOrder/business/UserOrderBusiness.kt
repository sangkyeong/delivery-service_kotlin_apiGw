package org.delivery.api.domain.userOrder.business

import org.delivery.api.common.Log
import org.delivery.api.domain.store.converter.StoreConverter
import org.delivery.api.domain.store.service.StoreService
import org.delivery.api.domain.storeMenu.converter.StoreMenuConverter
import org.delivery.api.domain.storeMenu.service.StoreMenuService
import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.userOrder.controller.model.UserOrderDetailResponse
import org.delivery.api.domain.userOrder.controller.model.UserOrderRequest
import org.delivery.api.domain.userOrder.controller.model.UserOrderResponse
import org.delivery.api.domain.userOrder.converter.UserOrderConverter
import org.delivery.api.domain.userOrder.producer.UserOrderProducer
import org.delivery.api.domain.userOrder.service.UserOrderService
import org.delivery.api.domain.userOrderMenu.converter.UserOrderMenuConverter
import org.delivery.api.domain.userOrderMenu.service.UserOrderMenuService
import org.delivery.common.annotation.Business
import org.delivery.db.userOrderMenu.enums.UserOrderMenuStatus

@Business
class UserOrderBusiness (
    private val userOrderService: UserOrderService,
    private val storeMenuService: StoreMenuService,
    private val userOrderMenuService: UserOrderMenuService,
    private val storeService: StoreService,

    private val userOrderConverter: UserOrderConverter,
    private val userOrderMenuConverter: UserOrderMenuConverter,
    private val storeMenuConverter: StoreMenuConverter,
    private val storeConverter: StoreConverter,

    private val userOrderProducer: UserOrderProducer

){
    companion object: Log

    fun userOrder(
        user: User?,
        body: UserOrderRequest?
    ): UserOrderResponse{
        //가게찾기
        val storeEntity = storeService.getStoreWithThrow(body?.storeId)

        //주문한 메뉴 찾기
        val storeMenuEntityList = body?.storeMenuIdList
            ?.mapNotNull {
                storeMenuService.getStoreMenuWithThrow(it)
            }?.toList()

        val userOrderEntity = userOrderConverter.toEntity(
            user = user,
            storeEntity = storeEntity,
            storeMenuEntityList = storeMenuEntityList
        ).run { userOrderService.order(this) }

        //주문
        val newUserOrderEntity = userOrderService.order(userOrderEntity)

        //맵핑
        val userOrderMenuEntityList = storeMenuEntityList
        ?.map {userOrderMenuConverter.toEntity(
            newUserOrderEntity, it)}
        ?.toList()

        //주문내역 기록남기기
        userOrderMenuEntityList
        ?.forEach {userOrderMenuService.order(it)}

        //비동기로 가맹점에 주문알리기
        userOrderProducer.sendOrder(newUserOrderEntity)

        //response
        return userOrderConverter.toResponse(newUserOrderEntity)
    }

    fun current(
        user: User?
    ): List<UserOrderDetailResponse>?{
        return userOrderService.current(user?.id).map {
            userOrderEntity ->
            //주문1건씩처리
            //사용자가 주문한 메뉴
            val storeMenuEntityList = userOrderEntity.userOrderMenuList?.stream()
                ?.filter { userOrderMenuEntity ->
                    userOrderMenuEntity.status == UserOrderMenuStatus.REGISTERED }
                ?.map { userOrderMenuEntity ->
                    userOrderMenuEntity.storeMenu }
                ?.toList()

           UserOrderDetailResponse(
               userOrderResponse = userOrderConverter.toResponse(userOrderEntity),
               storeMenuResponseList = storeMenuConverter.toResponse(storeMenuEntityList),
               storeResponse = storeConverter.toResponse(userOrderEntity.store)
           )
         }.toList()
    }

    fun history(
        user: User?
    ): List<UserOrderDetailResponse>?{
        return userOrderService.history(user?.id).map {
                userOrderEntity ->
            //주문1건씩처리
            //사용자가 주문한 메뉴
            val storeMenuEntityList = userOrderEntity.userOrderMenuList?.stream()
                ?.filter { userOrderMenuEntity ->
                    userOrderMenuEntity.status == UserOrderMenuStatus.REGISTERED }
                ?.map { userOrderMenuEntity ->
                    userOrderMenuEntity.storeMenu }
                ?.toList()

            UserOrderDetailResponse(
                userOrderResponse = userOrderConverter.toResponse(userOrderEntity),
                storeMenuResponseList = storeMenuConverter.toResponse(storeMenuEntityList),
                storeResponse = storeConverter.toResponse(userOrderEntity.store)
            )
        }.toList()
    }

    fun read(
        user: User?,
        orderId: Long?
    ):UserOrderDetailResponse{
        return userOrderService.getUserOrderWithOutStatusWithThrow(orderId, user?.id)
            .let{userOrderEntity ->

            val storeMenuEntityList = userOrderEntity.userOrderMenuList
                ?.stream()
                ?.filter {it.status == UserOrderMenuStatus.REGISTERED }
                ?.map{it.storeMenu }
                ?.toList()
                ?: listOf()

                UserOrderDetailResponse(
                    userOrderResponse = userOrderConverter.toResponse(userOrderEntity),
                    storeMenuResponseList = storeMenuConverter.toResponse(storeMenuEntityList),
                    storeResponse = storeConverter.toResponse(userOrderEntity.store)
                )
        }
    }
}