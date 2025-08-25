package org.amex.repository

import org.amex.entity.User
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository : PanacheRepository<User> {

    fun findByUsername(username: String): User? =
        find("username", username).firstResult()
}
