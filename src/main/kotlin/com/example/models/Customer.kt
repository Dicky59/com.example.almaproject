package com.example.models

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

interface Customer : Entity<Customer> {
    companion object : Entity.Factory<Customer>()

    val id: Long?
    var name: String
    var email: String?
    var homepage: String?
    var businessId: String?
    var streetAddress: String?
    var phone: String?
}

object Customers : Table<Customer>("customer") {
    val id = long("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val email = varchar("email").bindTo { it.email }
    val homepage = varchar("homepage").bindTo { it.homepage }
    val businessId = varchar("businessid").bindTo { it.businessId }
    val streetAddress = varchar("streetaddress").bindTo { it.streetAddress }
    val phone = varchar("phone").bindTo { it.phone }
}