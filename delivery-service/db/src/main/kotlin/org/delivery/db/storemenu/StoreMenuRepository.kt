package org.delivery.db.storemenu

import org.delivery.db.storemenu.enums.StoreMenuStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreMenuRepository : JpaRepository<storeMenuEntity, Long> {
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreMenuStatus?): storeMenuEntity?
    fun findAllByStoreIdAndStatusOrderBySequenceDesc(storeId: Long?, status: StoreMenuStatus?): List<storeMenuEntity>
}
