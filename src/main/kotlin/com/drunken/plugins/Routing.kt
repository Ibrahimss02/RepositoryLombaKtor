package com.drunken.plugins

import com.drunken.route.LombaRoute
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.resources.*

fun Application.configureRouting() {

    val route = LombaRoute()

    install(Resources)

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        route.apply {
            initAllRoutes()
        }
    }
}
