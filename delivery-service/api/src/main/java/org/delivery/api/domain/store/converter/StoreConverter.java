package org.delivery.api.domain.store.converter;

import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.common.annotation.Converter;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.store.StoreEntity;

import java.util.Optional;

@Converter
public class StoreConverter {
    public StoreEntity toEntity(
            StoreRegisterRequest req
    ){
     return Optional.ofNullable(req)
            .map(it -> {
                return  StoreEntity.builder()
                        .name(req.getName())
                        .address(req.getAddress())
                        .category(req.getStoreCategory())
                        .minimumAmount(req.getMinimumAmount())
                        .minimumDeliveryAmount(req.getMinimumDeliveryAmount())
                        .phoneNumber(req.getPhoneNumber())
                        .thumbnailUrl(req.getThumbnailUrl())
                        .build();
            }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreResponse toResponse(
            StoreEntity entity
    ){
        return Optional.ofNullable(entity)
                .map(it -> {
                    return  StoreResponse.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .status(entity.getStatus())
                        .category(entity.getCategory())
                        .address(entity.getAddress())
                        .minimumAmount(entity.getMinimumAmount())
                        .minimumDeliveryAmount(entity.getMinimumDeliveryAmount())
                        .phoneNumber(entity.getPhoneNumber())
                        .thumbnailUrl(entity.getThumbnailUrl())
                        .star(entity.getStar())
                        .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
