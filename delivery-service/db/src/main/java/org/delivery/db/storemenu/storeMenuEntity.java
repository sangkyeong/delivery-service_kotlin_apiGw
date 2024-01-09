package org.delivery.db.storemenu;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "store_menu")
public class storeMenuEntity extends BaseEntity {

    @JoinColumn(nullable = false)
    @ManyToOne
    private StoreEntity store;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 11, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, length = 50, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;

    @Column(nullable = false, length = 200)
    private String thumbnailUrl;

    private int likeCount;

    private int sequence;
}
