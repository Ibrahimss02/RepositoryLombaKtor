package com.drunken

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.drunken.plugins.*
import io.ktor.server.application.*
import io.ktor.server.resources.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}