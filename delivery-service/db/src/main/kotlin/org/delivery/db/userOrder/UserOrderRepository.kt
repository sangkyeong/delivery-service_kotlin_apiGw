package org.delivery.db.userOrder

import org.delivery.db.userOrder.enums.UserOrderStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserOrderRepository : JpaRepository<UserOrderEntity, Long> {
    //특정유저의 모든 주문
    //select * from user_order where user_id = ? and status = "REGISTERED" order by id desc
    fun findAllByUserIdAndStatusOrderByIdDesc(userId: Long?, status: UserOrderStatus?): List<UserOrderEntity>

    //select * from user_order where user_id = ? and status in(?)order by id desc
    fun findAllByUserIdAndStatusInOrderByIdDesc(userId: Long?, status: List<UserOrderStatus>?): List<UserOrderEntity>

    //특정주문
    //select * from user_order where id = ? and status = "REGISTERED" and user_id = ?
    fun findAllByIdAndStatusAndUserId(id: Long?, status: UserOrderStatus?, userId: Long?): UserOrderEntity?
    fun findAllByIdAndUserId(id: Long?, userId: Long?): UserOrderEntity?
}
