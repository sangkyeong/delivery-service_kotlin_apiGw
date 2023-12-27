package org.delivery.api.domain.userOrder.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userOrder.business.UserOrderBusiness;
import org.delivery.api.domain.userOrder.controller.model.UserOrderDetailResponse;
import org.delivery.api.domain.userOrder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userOrder.controller.model.UserOrderResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //현재 진행중인 주문건
    @GetMapping("/current")
    public Api<List<UserOrderDetailResponse>> current(
            @UserSession
            @Parameter(hidden = true)
            User user
    ){
        var response = userOrderBusiness.current(user);
        return Api.ok(response);
    }

    //과거 주문내역
    @GetMapping("/history")
    public Api<List<UserOrderDetailResponse>> history(
            @UserSession
            @Parameter(hidden = true)
            User user
    ){
        var response = userOrderBusiness.history(user);
        return Api.ok(response);
    }

    //주문 1건에 대한 내역
    @GetMapping("/id/{orderId}")
    public Api<UserOrderDetailResponse> read(
            @Parameter(hidden = true)
            @UserSession
            User user,
            @PathVariable Long orderId

    ){
        var response = userOrderBusiness.read(user,orderId);
        return Api.ok(response);
    }
}
