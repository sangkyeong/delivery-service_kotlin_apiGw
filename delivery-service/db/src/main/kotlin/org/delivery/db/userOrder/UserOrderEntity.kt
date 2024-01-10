package org.delivery.db.userOrder

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.delivery.db.store.StoreEntity
import org.delivery.db.userOrder.enums.UserOrderStatus
import org.delivery.db.userOrderMenu.UserOrderMenuEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "user_order")
class UserOrderEntity (
    /**
     * data class는 자동으로 hash와 equals, toString이 자동으로 생기는데
     * oneToMany는 기본으로 LAZY로 fetch가 되고
     * 연관관계된 데이터가 필요할때 생성이 되기 때문에 toString에서 문제가 생김
     * 이럴땐 data class를 사용하지 않는 걸 추천
     *  직접 toString생성
     */

    
    //자바에선 BaseEntity상속 받았으나 여기선 상속을 받지 않고 직접 추가
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?= null,

    @field:Column(nullable = false)
    var userId: Long?= null,

    @field:JoinColumn(nullable = false, name = "store_id")
    @field:ManyToOne
    var store: StoreEntity?= null,

    @field:Column(nullable = false, length = 50, columnDefinition = "varchar")
    @field:Enumerated(EnumType.STRING)
    var status: UserOrderStatus?= null,

    @field:Column(nullable = false, precision = 11, scale = 4)
    var amount: BigDecimal?= null,

    var orderedAt: LocalDateTime?= null,

    var acceptedAt: LocalDateTime?= null,

    var cookingStartedAt: LocalDateTime?= null,

    var deliveryStartedAt: LocalDateTime?= null,

    var receivedAt: LocalDateTime?= null,

    @field:OneToMany(mappedBy = "userOrder")            //userOrderEntity라는 곳에 맵핑
    @field:JsonIgnore
    var userOrderMenuList: MutableList<UserOrderMenuEntity>?=null

    ){

    override fun toString(): String {
        return "UserOrderEntity(id=$id, " +
                "userId=$userId, " +
                "store=$store, " +
                "status=$status, " +
                "amount=$amount, " +
                "orderedAt=$orderedAt, " +
                "acceptedAt=$acceptedAt, " +
                "cookingStartedAt=$cookingStartedAt, " +
                "deliveryStartedAt=$deliveryStartedAt, " +
                "receivedAt=$receivedAt, "
                //"userOrderMenuList=$userOrderMenuList)" //무한루프문제로 얘만 제외
    }
}