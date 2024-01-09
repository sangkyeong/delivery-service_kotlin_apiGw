package org.delivery.db.userOrderMenu

import org.delivery.db.userOrderMenu.enums.UserOrderMenuStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserOrderMenuRepository : JpaRepository<UserOrderMenuEntity, Long> {
    fun findAllByUserOrderIdAndStatus(userOrderId: Long?, status: UserOrderMenuStatus?): List<UserOrderMenuEntity>
}
