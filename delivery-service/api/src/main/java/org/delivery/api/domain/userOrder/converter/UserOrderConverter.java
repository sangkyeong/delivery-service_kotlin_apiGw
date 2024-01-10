package org.delivery.api.domain.userOrder.converter;

/*@Converter
public class UserOrderConverter {

    public UserOrderEntity toEntity(
            User user,
            StoreEntity storeEntity,
            List<storeMenuEntity> storeMenuEntityList
    ){
        var totalAmount = storeMenuEntityList.stream()
                .map((it -> it.getAmount()))
                .reduce(BigDecimal.ZERO*//*기본값은 0*//*, BigDecimal::add *//* 총합 *//*);

        return UserOrderEntity.builder()
                .userId(user.getId())
                .store(storeEntity)
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
}*/
