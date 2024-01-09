package org.delivery.db.store

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<StoreEntity, Long> {
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreStatus?): StoreEntity?
    fun findAllByStatusOrderByIdDesc(status: StoreStatus?): List<StoreEntity>
    fun findAllByStatusAndCategoryOrderByStatusDesc(
        status: StoreStatus?,
        storeCategory: StoreCategory?
    ): List<StoreEntity>

    fun findFirstByNameAndStatusOrderByIdDesc(name: String?, status: StoreStatus?): StoreEntity?
}
