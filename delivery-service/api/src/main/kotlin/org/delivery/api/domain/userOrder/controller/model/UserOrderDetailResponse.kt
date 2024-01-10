package org.delivery.api.domain.userOrder.controller.model

import org.delivery.api.domain.store.controller.model.StoreResponse
import org.delivery.api.domain.storeMenu.controller.model.StoreMenuResponse

data class UserOrderDetailResponse (

   val userOrderResponse: UserOrderResponse? = null,
   val storeResponse: StoreResponse? = null,
   val storeMenuResponseList: List<StoreMenuResponse>? = null
)