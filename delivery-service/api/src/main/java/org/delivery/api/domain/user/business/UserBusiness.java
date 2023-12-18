package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;

    private final UserConverter userConverter;

    private final TokenBusiness tokenBusiness;

    /*가입처리로직
    * 1. request -> entity
    * 2. entity-> save
    * 3. save Entity -> reponse
    * 4. response return
    * */
    public UserResponse register(UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register((entity));
        var response = userConverter.toResponse(newEntity);
        return response;

/*       람다식 표현
 return Optional.ofNullable((request))
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));*/
    }

    /**
     * 1. email, password 를 가지고 사용자 체크
     * 2. user entity  로그인 확인
     * 3. 토큰생성
     * 4. 생성 response
     */
    public TokenResponse login(UserLoginRequest req) {
        var userEntity = userService.login(req.getEmail(), req.getPassword());
        //토큰 생성
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }

    public UserResponse me(User user) {
        var userEntity = userService.getUserWithThrow(user.getId());
        var res = userConverter.toResponse(userEntity);
        return res;
    }
}
