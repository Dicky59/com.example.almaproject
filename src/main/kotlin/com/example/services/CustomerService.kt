package com.example.services

import com.example.models.Customer
import com.example.models.CustomerRequest
import com.example.models.Customers
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toSet

class CustomerService {

    private val database = Database.connect(
        url = "jdbc:postgresql://localhost:5438/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres"
    )

    suspend fun createCustomer(customerRequest: CustomerRequest): Boolean {

        val newCustomer = Customer {
            name = customerRequest.name
            email = customerRequest.email
            homepage = customerRequest.homepage!!
            businessId = customerRequest.businessId!!
            streetAddress = getCustomerAddress(businessId!!)
            phone = getCustomerPhone(businessId!!)
        }

        val affectedRecordsNumber =
            database.sequenceOf(Customers)
                .add(newCustomer)
        return affectedRecordsNumber == 1
    }

    fun findAllCustomers(): Set<Customer> =
        database.sequenceOf(Customers).toSet()

    fun findCustomerById(customerId: Long): Customer? =
        database.sequenceOf(Customers)
            .find { customer -> customer.id eq customerId }

    fun deleteCustomerById(customerId: Long): Boolean {
        val foundCustomer = findCustomerById(customerId)

        val affectedRecordsNumber = foundCustomer?.delete()

        return affectedRecordsNumber == 1
    }
}