package org.amex.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "\"User\"")
class User : PanacheEntity() {
    @Column(unique = true)
    lateinit var username: String

    lateinit var password: String
}