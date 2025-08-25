package org.amex.service

import org.amex.entity.User
import org.amex.repository.UserRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class UserService @Inject constructor(
    private val userRepository: UserRepository
) {

    @Transactional
    fun register(user: User): User {
        if (userRepository.findByUsername(user.username) != null) {
            throw IllegalArgumentException("Username already exists")
        }
        userRepository.persist(user)
        return user
    }

    fun login(username: String, password: String): Boolean {
        val user = userRepository.findByUsername(username)
        return user != null && user.password == password
    }

    fun listAll(): List<User> = userRepository.listAll()
}
