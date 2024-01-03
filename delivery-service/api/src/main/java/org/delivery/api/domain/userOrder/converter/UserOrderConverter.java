package org.delivery.api.domain.userOrder.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userOrder.controller.model.UserOrderResponse;
import org.delivery.db.storemenu.storeMenuEntity;
import org.delivery.db.userOrder.UserOrderEntity;

import java.math.BigDecimal;
import java.util.List;

@Converter
public class UserOrderConverter {

    public UserOrderEntity toEntity(
            User user,
            Long storeId,
            List<storeMenuEntity> storeMenuEntityList
    ){
        var totalAmount = storeMenuEntityList.stream()
                .map((it -> it.getAmount()))
                .reduce(BigDecimal.ZERO/*기본값은 0*/, BigDecimal::add /* 총합 */);

        return UserOrderEntity.builder()
                .userId(user.getId())
                .storeId(storeId)
                .amount(totalAmount)
                .build();
    }

    public UserOrderResponse toResponse(
            UserOrderEntity userOrderEntity
    ){
        return UserOrderResponse.builder()
                .id(userOrderEntity.getId())
                .orderedAt(userOrderEntity.getOrderedAt())
                .status(userOrderEntity.getStatus())
                .acceptedAt(userOrderEntity.getAcceptedAt())
                .amount(userOrderEntity.getAmount())
                .cookingStartedAt(userOrderEntity.getCookingStartedAt())
                .deliveryStartedAt(userOrderEntity.getDeliveryStartedAt())
                .receivedAt(userOrderEntity.getReceivedAt())
                .build();
    }
}
