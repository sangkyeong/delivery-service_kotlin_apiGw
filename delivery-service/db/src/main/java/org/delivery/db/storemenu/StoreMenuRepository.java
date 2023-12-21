package org.delivery.db.storemenu;

import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreMenuRepository extends JpaRepository<storeMenuEntity, Long> {

    Optional<storeMenuEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreMenuStatus status);

    List<storeMenuEntity> findAllByStoreIdAndStatusOrderBySequenceDesc(Long storeId, StoreMenuStatus status);
}
