package org.delivery.db.userOrder;

/*public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {
    
    //특정유저의 모든 주문
    //select * from user_order where user_id = ? and status = "REGISTERED" order by id desc
    List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    //select * from user_order where user_id = ? and status in(?)order by id desc
    List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> status);
    
    //특정주문
    //select * from user_order where id = ? and status = "REGISTERED" and user_id = ?
    Optional<UserOrderEntity> findAllByIdAndStatusAndUserId(Long id, UserOrderStatus status, Long userId);

    Optional<UserOrderEntity> findAllByIdAndUserId(Long id, Long userId);
}*/
