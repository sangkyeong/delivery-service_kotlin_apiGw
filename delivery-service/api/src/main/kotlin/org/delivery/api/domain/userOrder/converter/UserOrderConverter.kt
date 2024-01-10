package org.delivery.api.domain.userOrder.converter

import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.userOrder.controller.model.UserOrderResponse
import org.delivery.common.annotation.Converter
import org.delivery.db.store.StoreEntity
import org.delivery.db.storemenu.storeMenuEntity
import org.delivery.db.userOrder.UserOrderEntity

@Converter
class UserOrderConverter {
    fun toEntity(
        user: User?,
        storeEntity: StoreEntity?,
        storeMenuEntityList: List<storeMenuEntity>?
    ): UserOrderEntity {
        val totalMount = storeMenuEntityList
            ?.mapNotNull {
                it -> it.amount
            }?.reduce{
                 acc, bigDecimal -> acc.add(bigDecimal)
            }

        return UserOrderEntity(
            userId = user?.id,
            store = storeEntity,
            amount = totalMount
        )
    }

    fun toResponse(
        userOrderEntity: UserOrderEntity?
    ) : UserOrderResponse {
        return UserOrderResponse(
            id = userOrderEntity?.id,
            orderedAt = userOrderEntity?.orderedAt,
            status = userOrderEntity?.status,
            acceptedAt = userOrderEntity?.acceptedAt,
            amount = userOrderEntity?.amount,
            cookingStartedAt = userOrderEntity?.cookingStartedAt,
            deliveryStartedAt = userOrderEntity?.deliveryStartedAt,
            receivedAt = userOrderEntity?.receivedAt
        )
    }
}