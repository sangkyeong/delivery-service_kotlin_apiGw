package org.delivery.storeadmin.domain.storeMenu.converter;

import org.delivery.db.storemenu.storeMenuEntity;
import org.delivery.storeadmin.domain.storeMenu.controller.model.StoreMenuResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreMenuConverter {
    public StoreMenuResponse toResponse(storeMenuEntity storeMenuEntity){
        return StoreMenuResponse.builder()
                .id(storeMenuEntity.getId())
                .amount(storeMenuEntity.getAmount())
                .name(storeMenuEntity.getName())
                .status(storeMenuEntity.getStatus())
                .sequence(storeMenuEntity.getSequence())
                .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                .likeCount(storeMenuEntity.getLikeCount())
                .build();
    }

    public List<StoreMenuResponse> toResponse(List<storeMenuEntity> list){
        return list.stream()
                .map(it -> {
                    return toResponse(it);
                }).collect(Collectors.toList());
    }
}
