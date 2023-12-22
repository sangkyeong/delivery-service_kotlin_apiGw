package org.delivery.api.domain.userOrder.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storeMenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userOrder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userOrder.controller.model.UserOrderResponse;
import org.delivery.api.domain.userOrder.converter.UserOrderConverter;
import org.delivery.api.domain.userOrder.service.UserOrderService;
import org.delivery.api.domain.userOrderMenu.converter.UserOrderMenuConverter;
import org.delivery.api.domain.userOrderMenu.service.UserOrderMenuService;

import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private  final UserOrderService userOrderService;
    private final StoreMenuService storeMenuService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;

    //1. 사용자, 메뉴 id
    //2. userOrder 생성
    //3. userOrderMenu 생성
    //4. 응답 생성
    public UserOrderResponse userOrder(User user, UserOrderRequest body) {
        var storeMenuEntityList = body.getStoreMenuIdList().stream()
                .map(it -> storeMenuService.getStoreMenuWithThrow(it))
                .collect(Collectors.toList());

        var userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);

        //주문
        var newUserOrderEntity = userOrderService.order(userOrderEntity);

        //맵핑
        var userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> {
                    //menu + user order
                    var userOrderMenuEntity = userOrderMenuConverter.toEntity(newUserOrderEntity, it);
                    return userOrderMenuEntity;
                }).collect(Collectors.toList());

        //주문내역 기록 남기기
        userOrderMenuEntityList.forEach(it -> {
            userOrderMenuService.order(it);
        });


        //response
        return userOrderConverter.toResponse(newUserOrderEntity);
    }
}
