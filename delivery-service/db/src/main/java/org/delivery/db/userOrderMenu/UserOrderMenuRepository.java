package org.delivery.db.userOrderMenu;

import org.delivery.db.userOrderMenu.enums.UserOrderMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderMenuRepository extends JpaRepository<UserOrderMenuEntity, Long> {

    List<UserOrderMenuEntity> findAllByUserOrderIdAndStatus(Long userOrderId, UserOrderMenuStatus status);
}
