package com.drunken.route

import com.drunken.data.RepositoryLomba
import com.drunken.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*


class LombaRoute {

    private val repository = RepositoryLomba()

    suspend inline fun <reified T> ApplicationCall.response(noinline action: suspend () -> T) {
        try {
            this.respond(
                HttpStatusCode.OK,
                action()!!
            )
        } catch (e: Exception) {
            this@response.error(e)
        }
    }

    suspend inline fun ApplicationCall.error(exception: Exception) {
        when (exception) {
            is BadRequestException -> this.respond(HttpStatusCode.BadRequest, exception)
            is NotFoundException -> this.respond(HttpStatusCode.NotFound, exception)
            else -> this.respond(HttpStatusCode.Conflict, exception)
        }
    }

    private fun Route.getAllLombas() {
        get<RouteLocation.Lomba> {
            call.response {
                repository.getAllLomba()
            }
        }
    }

    private fun Route.getLombaDetail() {
        get<RouteLocation.Lomba.Id> {
            val lombaId = try {
                call.parameters["lombaId"]
            } catch (e: Exception) {
                call.error(e)
                return@get
            } ?: ""

            call.response {
                repository.getLombaDetail(lombaId)
            }
        }
    }

    private fun Route.getKelompok() {
        get<RouteLocation.Kelompok.Id> {
            val kelompokId = try {
                call.parameters["kelompokId"]
            } catch (e: Exception) {
                call.error(e)
                return@get
            } ?: ""

            call.response {
                repository.getKelompokDetail(kelompokId)
            }
        }
    }

    private fun Route.getUser() {
        get<RouteLocation.User.Id> {
            val userId = try {
                call.parameters["userId"]
            } catch (e: Exception) {
                call.error(e)
                return@get
            } ?: ""

            call.response {
                repository.getUser(userId)
            }
        }
    }

    private fun Route.addNewLomba() {
        post<RouteLocation.Lomba> {
            val body = try {
                call.receive<LombaBody>()
            } catch (e: Exception) {
                call.error(e)
                return@post
            }

            call.response {
                repository.addNewLomba(body)
            }
        }
    }

    private fun Route.addNewUser() {
        post<RouteLocation.User> {
            val body = try {
                call.receive<UserBody>()
            } catch (e: Exception) {
                call.error(e)
                return@post
            }

            call.response {
                repository.addNewUser(body)
            }
        }
    }

    private fun Route.addNewKelompok() {
        post<RouteLocation.Kelompok> {
            val body = try {
                call.receive<KelompokBody>()
            } catch (e: Exception) {
                call.error(e)
                return@post
            }

            call.response {
                repository.addNewKelompok(body)
            }
        }
    }

    private fun Route.addNewAnggotaKelompok() {
        post<RouteLocation.Kelompok.Id.Anggota> {
            val body = try {
                call.receive<KeanggotaanBody>()
            } catch (e: Exception) {
                call.error(e)
                return@post
            }

            call.response {
                repository.addNewAnggotaKelompok(body)
            }
        }
    }

    private fun Route.addNewHistoryLomba() {
        post<RouteLocation.Lomba.Id.History> {
            val body = try {
                call.receive<HistoryLombaBody>()
            } catch (e: Exception) {
                call.error(e)
                return@post
            }

            call.response {
                repository.addNewHistoryLomba(body)
            }
        }
    }

    fun Route.initAllRoutes() {
        getAllLombas()
        getLombaDetail()
        getKelompok()
        getUser()
        addNewLomba()
        addNewUser()
        addNewKelompok()
        addNewAnggotaKelompok()
        addNewHistoryLomba()
    }

}