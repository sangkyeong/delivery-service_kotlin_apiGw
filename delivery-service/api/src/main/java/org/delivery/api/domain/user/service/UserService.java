package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.error.UserErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

//User 도메인 로직 처리
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity Null"));

    }

    public UserEntity login(
        String email,
        String password
    ){
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    public UserEntity getUserWithThrow(
        String email,
        String password
    ){
        return Optional.ofNullable(userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
            email,
            password,
            UserStatus.REGISTERED
        )).orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(
           Long userId
    ){
        return Optional.ofNullable(userRepository.findFirstByIdAndStatusOrderByIdDesc(
                userId,
                UserStatus.REGISTERED
        )).orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
