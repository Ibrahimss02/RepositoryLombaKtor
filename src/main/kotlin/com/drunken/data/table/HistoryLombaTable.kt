package com.drunken.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object HistoryLombaTable: Table() {
    override val tableName: String
        get() = "history_lomba"

    val idLomba = reference("id_lomba", LombaTable.uid)
    val idKelompok = reference("id_kelompok", KelompokTable.uid)
    val tanggal = date("tanggal")
    val hasil = varchar("hasil_lomba", 128)
    val linkSubmisson = varchar("link_submission", 128)
}