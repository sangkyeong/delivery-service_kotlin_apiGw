package org.delivery.storeadmin.domain.storeuser.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storeUser.StoreUserEntity;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUserConverter {

    public StoreUserEntity toEntity(
            StoreUserRegisterRequest request,
            StoreEntity storeEntity
    ){
        return StoreUserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .storeId(storeEntity.getId())
                .build();
    }

    public StoreUserResponse toResponse(
            StoreUserEntity storeUserEntity,
            StoreEntity storeEntity
    ){
        return StoreUserResponse.builder()
                .user(StoreUserResponse.UserResponse.builder()
                        .id(storeUserEntity.getId())
                        .role(storeUserEntity.getRole())
                        .email(storeUserEntity.getEmail())
                        .status(storeUserEntity.getStatus())
                        .lastLoginAt(storeUserEntity.getLastLoginAt())
                        .registeredAt(storeUserEntity.getRegisteredAt())
                        .unregisteredAt(storeUserEntity.getUnregisteredAt())
                        .build()
                )
                .store(StoreUserResponse.StoreResponse.builder()
                        .id(storeEntity.getId())
                        .name(storeEntity.getName())
                        .build()
                )
                .build();
    }

    public StoreUserResponse toResponse(
            UserSession userSession
    ){
        return StoreUserResponse.builder()
                .user(StoreUserResponse.UserResponse.builder()
                        .id(userSession.getUserId())
                        .role(userSession.getRole())
                        .email(userSession.getEmail())
                        .status(userSession.getStatus())
                        .lastLoginAt(userSession.getLastLoginAt())
                        .registeredAt(userSession.getRegisteredAt())
                        .unregisteredAt(userSession.getUnregisteredAt())
                        .build()
                )
                .store(StoreUserResponse.StoreResponse.builder()
                        .id(userSession.getStoreId())
                        .name(userSession.getStoreName())
                        .build()
                )
                .build();
    }
}
