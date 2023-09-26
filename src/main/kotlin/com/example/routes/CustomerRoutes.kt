package com.example.routes

import com.example.models.Customer
import com.example.models.CustomerRequest
import com.example.models.CustomerResponse
import com.example.models.ErrorResponse
import com.example.services.CustomerService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private fun Customer?.toCustomerResponse(): CustomerResponse? =
    this?.let { CustomerResponse(
        it.id!!,
        it.name,
        it.email,
        it.homepage,
        it.businessId,
        it.streetAddress,
        it.phone
    ) }

fun Application.configureCustomerRoutes() {
    routing {
        route("/customer") {
            val customerService = CustomerService()
            createCustomer(customerService)
            getAllCustomersRoute(customerService)
            getCustomerByIdRoute(customerService)
            deleteCustomerByIdRoute(customerService)
        }
    }
}

fun Route.createCustomer(customerService: CustomerService) {
    post {
        val request = call.receive<CustomerRequest>()

        val success = customerService.createCustomer(customerRequest = request)

        if (success)
            call.respond(HttpStatusCode.Created)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create customer"))
    }
}

fun Route.getAllCustomersRoute(customerService: CustomerService) {
    get {
        val customers = customerService.findAllCustomers()
            .map(Customer::toCustomerResponse)

        call.respond(message = customers)
    }
}

fun Route.getCustomerByIdRoute(customerService: CustomerService) {
    get("/{id}") {
        val id: Long = call.parameters["id"]?.toLongOrNull()
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        customerService.findCustomerById(id)
            ?.let { foundCustomer -> foundCustomer.toCustomerResponse() }
            ?.let { response -> call.respond(response) }
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("Customer with id [$id] not found"))
    }
}

fun Route.deleteCustomerByIdRoute(customerService: CustomerService) {
    delete("/{id}") {
        val id: Long = call.parameters["id"]?.toLongOrNull()
            ?: return@delete call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        val success = customerService.deleteCustomerById(id)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot delete customer with id [$id]"))
    }
}