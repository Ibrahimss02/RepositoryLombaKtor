package com.drunken.util

import com.drunken.data.table.HistoryLombaTable
import com.drunken.data.table.KelompokTable
import com.drunken.data.table.LombaTable
import com.drunken.data.table.UserTable
import com.drunken.model.*
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToLombaResponse(
    historyLomba: List<HistoryLombaResponse>
) = LombaResponse(
    idLomba = this[LombaTable.uid],
    name = this[LombaTable.name],
    deskripsi = this[LombaTable.deskripsi],
    tanggal = this[LombaTable.tanggal],
    penyelenggara = this[LombaTable.penyelenggara],
    kategori = this[LombaTable.kategori],
    kategoriHadiah = this[LombaTable.kategoriHadiah],
    image = this[LombaTable.image],
    statusLomba = this[LombaTable.statusLomba],
    historyLomba = historyLomba
)

fun ResultRow.mapRowToUserResponse(
    historyLombaUser: List<HistoryLombaResponse>
) = UserResponse(
    idUser = this[UserTable.uid],
    nama = this[UserTable.name],
    prodi = this[UserTable.prodi],
    angkatan = this[UserTable.angkatan],
    jabatan = this[UserTable.jabatan],
    image = this[UserTable.image],
    historyLomba = historyLombaUser
)

fun ResultRow.mapRowToUserLiteResponse() = UserResponseLite(
    idUser = this[UserTable.uid],
    nama = this[UserTable.name],
    jabatan = this[UserTable.jabatan],
    image = this[UserTable.jabatan]
)

fun ResultRow.mapRowToKelompokResponse(
    daftarAnggota: List<UserResponseLite>,
    historyLombaKelompok: List<HistoryLombaKelompokResponse>
) = KelompokResponse(
    id = this[KelompokTable.uid],
    name = this[KelompokTable.name],
    daftarAnggota = daftarAnggota,
    historyLomba = historyLombaKelompok
)

fun ResultRow.mapRowToHistoryLombaKelompokResponse() = HistoryLombaKelompokResponse(
    id = this[LombaTable.uid],
    namaLomba = this[LombaTable.name],
    tahun = this[HistoryLombaTable.tanggal],
    hasil = this[HistoryLombaTable.hasil]
)

fun ResultRow.mapRowToHistoryLombaResponse() = HistoryLombaResponse(
    idKelompok = this[KelompokTable.uid],
    namaKelompok = this[KelompokTable.name],
    tahun = this[HistoryLombaTable.tanggal],
    hasil = this[HistoryLombaTable.hasil],
    linkSubmission = this[HistoryLombaTable.linkSubmisson]
)

fun ResultRow.mapRowToLombaLiteResponse() = LombaLiteResponse(
    idLomba = this[LombaTable.uid],
    name = this[LombaTable.name],
    tanggal = this[LombaTable.tanggal],
    penyelenggara = this[LombaTable.penyelenggara],
    kategori = this[LombaTable.kategori],
    image = this[LombaTable.image],
    statusLomba = this[LombaTable.statusLomba]
)