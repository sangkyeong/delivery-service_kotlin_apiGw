package org.delivery.storeadmin.domain.userOrderMenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.db.userOrderMenu.UserOrderMenuEntity;
import org.delivery.db.userOrderMenu.UserOrderMenuRepository;
import org.delivery.db.userOrderMenu.enums.UserOrderMenuStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserOrderMenuService {

    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUserOrderMenuList(Long userOrderId){
        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    }
}
