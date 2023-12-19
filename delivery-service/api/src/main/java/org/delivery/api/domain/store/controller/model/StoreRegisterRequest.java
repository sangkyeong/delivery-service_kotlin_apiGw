package org.delivery.api.domain.store.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.store.enums.StoreCategory;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRegisterRequest {

    @NotBlank
    private String name;

    @NotBlank   //"" X, " " X, null X
    private String address;

    @NotNull    //null X, 또한 enum형이라 문자로 볼 수 없음
    private StoreCategory storeCategory;

    @NotBlank
    private String thumbnailUrl;

    @NotNull //객체타입이라 notnull
    private BigDecimal minimumAmount;

    @NotNull
    private BigDecimal minimumDeliveryAmount;

    @NotBlank
    private String phoneNumber;
}
