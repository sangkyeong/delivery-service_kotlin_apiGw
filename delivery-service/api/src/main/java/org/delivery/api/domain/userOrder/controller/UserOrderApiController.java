package org.delivery.api.domain.userOrder.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userOrder.business.UserOrderBusiness;
import org.delivery.api.domain.userOrder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userOrder.controller.model.UserOrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-order")
@RequiredArgsConstructor
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    //사용자 주문
    @PostMapping("")
    public Api<UserOrderResponse> userOrder(
            @Valid
            @RequestBody
            Api<UserOrderRequest> userOrderRequest,

            @UserSession
            @Parameter(hidden = true)
            User user
    ){
        var response = userOrderBusiness.userOrder(user, userOrderRequest.getBody());
        return Api.ok(response);
    }
}
