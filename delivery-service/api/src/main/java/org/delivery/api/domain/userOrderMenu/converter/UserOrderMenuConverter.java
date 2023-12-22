package org.delivery.api.domain.userOrderMenu.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.userOrder.controller.model.UserOrderResponse;
import org.delivery.db.storemenu.storeMenuEntity;
import org.delivery.db.userOrder.UserOrderEntity;
import org.delivery.db.userOrderMenu.UserOrderMenuEntity;

@Converter
public class UserOrderMenuConverter {

    public UserOrderMenuEntity toEntity(
            UserOrderEntity userOrderEntity,
            storeMenuEntity storeMenuEntity
    ){
        return UserOrderMenuEntity.builder()
                .userOrderId(userOrderEntity.getId())
                .storeMenuId(storeMenuEntity.getId())
                .build();
    }
}
