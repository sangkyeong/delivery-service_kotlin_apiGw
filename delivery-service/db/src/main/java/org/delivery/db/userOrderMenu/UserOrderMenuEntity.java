package org.delivery.db.userOrderMenu;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.userOrderMenu.enums.UserOrderMenuStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "user_order_menu")
public class UserOrderMenuEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userOrderId;

    @Column(nullable = false)
    private Long storeMenuId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, columnDefinition = "varchar")
    private UserOrderMenuStatus status;
}
