package com.drunken.route

import io.ktor.resources.*
import kotlinx.serialization.Serializable

sealed class RouteLocation {

    @Serializable
    @Resource("/lomba")
    class Lomba() {
        @Serializable
        @Resource("{lombaId}")
        class Id(val parent: Lomba = Lomba(), val lombaId: String) {
            @Serializable
            @Resource("history")
            class History(val parent: Id)
        }
    }

    @Serializable
    @Resource("/kelompok")
    class Kelompok() {
        @Serializable
        @Resource("{kelompokId}")
        class Id(val parent: Kelompok = Kelompok(), val kelompokId: String) {
            @Serializable
            @Resource("anggota")
            class Anggota(val parent: Id)
        }
    }

    @Serializable
    @Resource("/user")
    class User() {
        @Serializable
        @Resource("{userId}")
        class Id(val parent: User = User(), val userId: String)
    }
}
