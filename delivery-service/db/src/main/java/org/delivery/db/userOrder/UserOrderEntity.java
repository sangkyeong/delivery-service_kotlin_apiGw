package org.delivery.db.userOrder;

/*@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "user_order")
public class UserOrderEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @JoinColumn(nullable = false, name = "store_id")
    @ManyToOne
    private StoreEntity store;

    @Column(nullable = false, length = 50, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private UserOrderStatus status;

    @Column(nullable = false, precision = 11, scale = 4)
    private BigDecimal amount;

    private LocalDateTime orderedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    private LocalDateTime receivedAt;

    @OneToMany(mappedBy = "userOrder")            //userOrderEntity라는 곳에 맵핑
    @ToString.Exclude                               //UserOrderMenuEntity와 UserOrderEntity를 무한루프 돌기때문에
                                                    //연관관계 컬럼 설정 시 해당 컬럼을 toString에 포함시키지 않는 @ToString.Exclude사용
    @JsonIgnore
    private List<UserOrderMenuEntity> userOrderMenuList;
}*/
