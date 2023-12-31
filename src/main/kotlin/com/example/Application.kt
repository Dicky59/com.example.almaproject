package com.example

import com.example.plugins.configureMonitoring
import com.example.plugins.configureSerialization
import com.example.routes.configureCustomerRoutes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureCustomerRoutes()
    configureSerialization()
    configureMonitoring()
}
