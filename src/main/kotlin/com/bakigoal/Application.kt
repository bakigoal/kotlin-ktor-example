package com.bakigoal

import com.bakigoal.routes.registerCustomerRoutes
import com.bakigoal.routes.registerOrderRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        anyHost()
    }
    registerCustomerRoutes()
    registerOrderRoutes()
}


