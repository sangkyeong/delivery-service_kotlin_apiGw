package org.delivery.db.storeUser

import org.delivery.db.storeUser.enums.StoreUserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreUserRepository : JpaRepository<StoreUserEntity, Long> {
    fun findFirstByEmailAndStatusOrderByIdDesc(email: String?, status: StoreUserStatus?): StoreUserEntity?
}
