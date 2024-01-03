package org.delivery.api.domain.userOrder.controller.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderRequest {

    //주문
    //특정사용자가, 특정메뉴를 주문
    //특정사용자 = 로그인된 세션에 들어있는 사용자
    //특정메뉴 id

    @NotNull
    private List<Long> storeMenuIdList;
}
