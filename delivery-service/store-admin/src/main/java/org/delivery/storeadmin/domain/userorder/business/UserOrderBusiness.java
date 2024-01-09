package org.delivery.storeadmin.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.storeadmin.domain.sse.connection.SseConnectionPool;
import org.delivery.storeadmin.domain.storeMenu.converter.StoreMenuConverter;
import org.delivery.storeadmin.domain.storeMenu.service.StoreMenuService;
import org.delivery.storeadmin.domain.userOrderMenu.service.UserOrderMenuService;
import org.delivery.storeadmin.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.storeadmin.domain.userorder.converter.UserOrderConverter;
import org.delivery.storeadmin.domain.userorder.service.UserOrderService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;

    private final SseConnectionPool sseConnectionPool;
    
    private final UserOrderMenuService userOrderMenuService;
    
    private final StoreMenuService storeMenuService;

    private final StoreMenuConverter storeMenuConverter;

    private final UserOrderConverter userOrderConverter;
    /**
     * 주문
     * 주문내역찾기
     * 스토어찾기
     * 연결된세션찾아서
     * 푸쉬
     * @param userOrderMessage
     */

    public void pushUserOrder(UserOrderMessage userOrderMessage){
        var userOrderEntity = userOrderService.getUserOrder(userOrderMessage.getUserOrderId()).orElseThrow(
                ()-> new RuntimeException("사용자 주문내역 없음"));

        var userOrderMenuList = userOrderMenuService.getUserOrderMenuList(userOrderEntity.getId());

        var storeMenuResponseList = userOrderMenuList.stream()
                .map(userOrderMenuEntity->{
                    return storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenu().getId());
                })
                .map(storeMenuEntity->{
                    return storeMenuConverter.toResponse(storeMenuEntity);
                }).collect(Collectors.toList());

        var userOrderResponse = userOrderConverter.toResponse(userOrderEntity);

        var push = UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderResponse)
                .storeMenuResponseList(storeMenuResponseList)
                .build();

        var userConnection = sseConnectionPool.getSession(userOrderEntity.getStore().toString());

        //사용자에게 푸쉬
        userConnection.sendMessage(push);
    }
}
