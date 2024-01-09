package org.delivery.api.domain.storeMenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.delivery.db.storemenu.storeMenuEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public storeMenuEntity getStoreMenuWithThrow(Long id){
        var entity = Optional.ofNullable(storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED));
        return entity.orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public List<storeMenuEntity> getStoreMenuByStoreId(Long storeId){
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId,StoreMenuStatus.REGISTERED);
    }

    public storeMenuEntity register(
            storeMenuEntity storeMenuEntity
    ){
        return Optional.ofNullable(storeMenuEntity)
                .map(it -> {
                    it.setStatus(StoreMenuStatus.REGISTERED);
                    return storeMenuRepository.save(it);
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }
}
