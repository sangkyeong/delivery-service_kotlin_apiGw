package org.delivery.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request){
        return Optional.ofNullable(request)
                .map(it->{
                    return UserEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    public UserResponse toResponse(UserEntity newEntity) {
        return Optional.ofNullable(newEntity)
                .map(it -> {
                    return UserResponse.builder()
                            .id(newEntity.getId())
                            .name(newEntity.getName())
                            .status(newEntity.getStatus())
                            .email(newEntity.getEmail())
                            .address(newEntity.getAddress())
                            .registeredAt(newEntity.getRegisteredAt())
                            .lastLoginAt(newEntity.getLastLoginAt())
                            .unregisteredAt(newEntity.getUnregisteredAt())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
