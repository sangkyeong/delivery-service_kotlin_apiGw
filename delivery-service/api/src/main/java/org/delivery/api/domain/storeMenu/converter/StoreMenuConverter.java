package org.delivery.api.domain.storeMenu.converter;

import org.delivery.api.domain.storeMenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storeMenu.controller.model.StoreMenuResponse;
import org.delivery.common.annotation.Converter;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.storeMenuEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {

    public storeMenuEntity toEntity(StoreMenuRegisterRequest request, StoreEntity storeEntity){
        return Optional.ofNullable(request)
                .map(it -> {
                    return storeMenuEntity.builder()
                            .store(storeEntity)
                            .name(request.getName())
                            .amount(request.getAmount())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreMenuResponse toResponse(storeMenuEntity storeMenuEntity){
        return Optional.ofNullable(storeMenuEntity)
                .map(it -> {
                    return StoreMenuResponse.builder()
                            .id(storeMenuEntity.getId())
                            .StoreId(storeMenuEntity.getStore().getId())
                            .name(storeMenuEntity.getName())
                            .amount(storeMenuEntity.getAmount())
                            .status(storeMenuEntity.getStatus())
                            .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                            .likeCount(storeMenuEntity.getLikeCount())
                            .sequence(storeMenuEntity.getSequence())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenuResponse> toResponse(
            List<storeMenuEntity> list
    ){
        return list.stream()
            .map(it->
                toResponse(it)
            ).collect(Collectors.toList());
    }
}
