package org.delivery.api.domain.userOrder.business;

/*@Business
@RequiredArgsConstructor
@Slf4j
public class UserOrderBusiness {

    private  final UserOrderService userOrderService;
    private final StoreMenuService storeMenuService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreService storeService;
    private final StoreMenuConverter storeMenuConverter;
    private final StoreConverter storeConverter;
    private final UserOrderProducer userOrderProducer;
    private final ObjectMapper objectMapper;

    //1. 사용자, 메뉴 id
    //2. userOrder 생성
    //3. userOrderMenu 생성
    //4. 응답 생성
    public UserOrderResponse userOrder(User user, UserOrderRequest body) {

        var storeEntity = storeService.getStoreWithThrow(body.getStoreId());

        var storeMenuEntityList = body.getStoreMenuIdList().stream()
                .map(it -> storeMenuService.getStoreMenuWithThrow(it))
                .collect(Collectors.toList());

        var userOrderEntity = userOrderConverter.toEntity(user, storeEntity, storeMenuEntityList);

        //주문
        var newUserOrderEntity = userOrderService.order(userOrderEntity);

        //맵핑
        var userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> {
                    //menu + user order
                    var userOrderMenuEntity = userOrderMenuConverter.toEntity(newUserOrderEntity, it);
                    return userOrderMenuEntity;
                }).collect(Collectors.toList());

        //주문내역 기록 남기기
        userOrderMenuEntityList.forEach(it -> {
            userOrderMenuService.order(it);
        });
        
        //비동기로 가맹점에 주문알리기
        userOrderProducer.sendOrder(newUserOrderEntity);


        //response
        return userOrderConverter.toResponse(newUserOrderEntity);
    }

    public List<UserOrderDetailResponse> current(User user) {
        var userOrderEntityList = userOrderService.current(user.getId());

        //주문1건씩 처리
        var userOrderDtailResponseList = userOrderEntityList.stream().map(userOrderEntity->{

            //사용자가 주문한 메뉴
            var userOrderMenuEntityList = userOrderEntity.getUserOrderMenuList()
                    .stream()
                    .filter(it-> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                    .toList();

            var storeMenuEntityList = userOrderMenuEntityList.stream().map(
                    UserOrderMenuEntity::getStoreMenu).collect(Collectors.toList());

            //사용자가 주문한 스토어
            var storeEntity = userOrderEntity.getStore();

            return UserOrderDetailResponse.builder()
                    .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                    .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                    .storeResponse(storeConverter.toResponse(storeEntity))
                    .build();
        }).collect(Collectors.toList());
        return userOrderDtailResponseList;
    }

    public List<UserOrderDetailResponse> history(User user) {
        var userOrderEntityList = userOrderService.history(user.getId());

        //주문1건씩 처리
        var userOrderDtailResponseList = userOrderEntityList.stream().map( userOrderEntity ->{

            *//*
                log.info("사용자의 주문정보 : {}", it);
                try {
                    var jsonString = objectMapper.writeValueAsString(it);
                    log.info("JsonString : {}", jsonString);
                } catch (JsonProcessingException e) {
                    log.info("", e);
                }
            *//*

            //사용자가 주문한 메뉴

            //기존엔 getUserOrderMenu로 쿼리를 보내 데이터를 처리
            //연관관계를 설정했기 때문에 데이터를 간단하게 가져올 수 있음
            //단, 자동으로 데이터를 처리해주는 만큼 잘못처리되는 곳이 있는지 확인필수
            //var userOrderMenuEntityList = userOrderMenuService.getUserOrderMenu(it.getId());
            var userOrderMenuEntityList = userOrderEntity.getUserOrderMenuList()
                    .stream()
                    .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                    .collect(Collectors.toList());



            var storeMenuEntityList = userOrderMenuEntityList.stream().map(
                    userOrderMenuEntity->{
                        //var storeMenuEntity = storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenu().getId());
                        //연관관계설정으로 바로 접근가능
                        return userOrderMenuEntity.getStoreMenu();
                    }).collect(Collectors.toList());

            
            //사용자가 주문한 스토어
            var storeEntity = userOrderEntity.getStore();       //연관관계설정으로 바로 접근가능
                    //storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStore().getId());
            return UserOrderDetailResponse.builder()
                    .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                    .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                    .storeResponse(storeConverter.toResponse(storeEntity))
                    .build();
        }).collect(Collectors.toList());
        return userOrderDtailResponseList;

    }

    public UserOrderDetailResponse read(User user, Long orderId) {
        var userOrderEntity = userOrderService.getUserOrderWithOutStatusWithThrow(orderId, user.getId());

        //사용자가 주문한 메뉴
        var userOrderMenuEntityList = userOrderEntity.getUserOrderMenuList()
                .stream()
                .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED)).collect(Collectors.toList());

        var storeMenuEntityList = userOrderMenuEntityList.stream().map(
                userOrderMenuEntity->{
                    return userOrderMenuEntity.getStoreMenu();
                }).collect(Collectors.toList());

        //사용자가 주문한 스토어
        var storeEntity = userOrderEntity.getStore();

        return UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                .storeResponse(storeConverter.toResponse(storeEntity))
                .build();
    }
}*/
