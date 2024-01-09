package org.delivery.api.domain.storeMenu.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.storeMenu.business.StoreMenuBusiness;
import org.delivery.api.domain.storeMenu.controller.model.StoreMenuResponse;
import org.delivery.common.api.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store-menu")
@RequiredArgsConstructor
public class StoreMenuApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @GetMapping("/search")
    public Api<List<StoreMenuResponse>> search(
            @RequestParam Long storeId
    ){
        var response = storeMenuBusiness.search(storeId);
        return Api.ok(response);
    }
}
