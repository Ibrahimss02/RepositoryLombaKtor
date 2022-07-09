package com.drunken.data

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.drunken.data.table.*
import com.drunken.model.*
import com.drunken.util.*
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RepositoryLomba {
    private var dbFactory: DatabaseFactory = DatabaseFactory()

    suspend fun addNewLomba(body: LombaBody) {
        dbFactory.dbQuery {
            LombaTable.insert { table ->
                table[uid] = "LOMBA${NanoIdUtils.randomNanoId()}"
                table[name] = body.name
                table[deskripsi] = body.deskripsi
                table[tanggal] = LocalDate.parse(body.tanggal, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                table[penyelenggara] = body.penyelenggara
                table[kategori] = body.kategori
                table[kategoriHadiah] = body.kategoriHadiah
                table[image] = body.image
                table[statusLomba] = body.statusLomba
            }
        }
    }

    suspend fun addNewUser(body: UserBody) {
        dbFactory.dbQuery {
            UserTable.insert { table ->
                table[uid] = "USER${NanoIdUtils.randomNanoId()}"
                table[name] = body.name
                table[prodi] = body.prodi
                table[angkatan] = body.angkatan
                table[jabatan] = body.jabatan
                table[image] = body.image
            }
        }
    }

    suspend fun addNewKelompok(body: KelompokBody) {
        dbFactory.dbQuery {
            KelompokTable.insert { table ->
                table[uid] = "KELOMPOK${NanoIdUtils.randomNanoId()}"
                table[name] = body.name
            }
        }
    }

    suspend fun addNewAnggotaKelompok(body: KeanggotaanBody) {
        dbFactory.dbQuery {
            KeanggotaanTable.insert { table ->
                table[idKelompok] = body.idKelompok
                table[idUser] = body.idUser
            }
        }
    }

    suspend fun addNewHistoryLomba(body: HistoryLombaBody) {
        dbFactory.dbQuery {
            HistoryLombaTable.insert { table ->
                table[idLomba] = body.idLomba
                table[idKelompok] = body.idKelompok
                table[tanggal] = LocalDate.parse(body.tanggal, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                table[hasil] = body.hasil
                table[linkSubmisson] = body.linkSubmission
            }
        }
    }

    suspend fun getAllLomba() = dbFactory.dbQuery {
        LombaTable.slice(
            LombaTable.uid,
            LombaTable.name,
            LombaTable.tanggal,
            LombaTable.penyelenggara,
            LombaTable.kategori,
            LombaTable.image,
            LombaTable.statusLomba
        )
            .selectAll()
            .groupBy(LombaTable.uid)
            .mapNotNull {
                it.mapRowToLombaLiteResponse()
            }
    }

    suspend fun getLombaDetail(lombaId: String): LombaResponse =
        dbFactory.dbQuery {
            val historyLomba = HistoryLombaTable.join(KelompokTable, JoinType.FULL)
                .select {
                HistoryLombaTable.idLomba eq lombaId
            }.mapNotNull {
                it.mapRowToHistoryLombaResponse()
            }

            LombaTable.slice(
                LombaTable.uid,
                LombaTable.name,
                LombaTable.deskripsi,
                LombaTable.tanggal,
                LombaTable.penyelenggara,
                LombaTable.kategori,
                LombaTable.kategoriHadiah,
                LombaTable.image,
                LombaTable.statusLomba
            )
                .select {
                    LombaTable.uid eq lombaId
                }
                .groupBy(LombaTable.uid)
                .firstNotNullOf {
                    return@dbQuery it.mapRowToLombaResponse(historyLomba)
                }
        }

    suspend fun getKelompokDetail(kelompokId: String): KelompokResponse =
        dbFactory.dbQuery {

            val historyLombaKelompok = HistoryLombaTable.join(LombaTable, JoinType.FULL)
                .select {
                    HistoryLombaTable.idKelompok.eq(kelompokId)
                }.mapNotNull {
                    it.mapRowToHistoryLombaKelompokResponse()
                }

            val listIdAnggota = KeanggotaanTable.select {
                KeanggotaanTable.idKelompok eq kelompokId
            }.mapNotNull {
                it[KeanggotaanTable.idUser]
            }

            val listAnggotaKelompok: MutableList<UserResponseLite> = mutableListOf()
            listIdAnggota.forEach {
                val listAnggota = UserTable.select {
                    UserTable.uid eq it
                }.mapNotNull {
                    it.mapRowToUserLiteResponse()
                }

                listAnggotaKelompok += listAnggota
            }

            val result = KelompokTable.slice(
                KelompokTable.uid,
                KelompokTable.name
            )
                .select {
                    KelompokTable.uid eq kelompokId
                }
                .groupBy(KelompokTable.uid)
                .firstNotNullOf {
                    it.mapRowToKelompokResponse(listAnggotaKelompok, historyLombaKelompok)
                }

            return@dbQuery result
        }


    suspend fun getUser(userId: String): UserResponse =
        dbFactory.dbQuery {
            val listIdKelompok = KeanggotaanTable.select {
                KeanggotaanTable.idUser eq userId
            }.mapNotNull {
                it[KeanggotaanTable.idKelompok]
            }

            val listHistoryLomba: MutableList<HistoryLombaResponse> = mutableListOf()
            listIdKelompok.forEach {
                val historyLomba = HistoryLombaTable.join(KelompokTable, JoinType.FULL)
                    .select {
                    HistoryLombaTable.idKelompok eq it
                }.mapNotNull {
                    it.mapRowToHistoryLombaResponse()
                }

                listHistoryLomba += historyLomba
            }

            UserTable.slice(
                UserTable.uid,
                UserTable.name,
                UserTable.prodi,
                UserTable.angkatan,
                UserTable.jabatan,
                UserTable.image
            )
                .select {
                    UserTable.uid eq userId
                }
                .groupBy(UserTable.uid)
                .firstNotNullOf {
                    return@dbQuery it.mapRowToUserResponse(listHistoryLomba)
                }
        }
}