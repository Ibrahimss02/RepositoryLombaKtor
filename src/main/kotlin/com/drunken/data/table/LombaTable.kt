package com.drunken.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object LombaTable: Table() {
    override val tableName: String
        get() = "lomba"

    val uid = varchar("id_lomba", 128)
    val name = varchar("name", 64)
    val deskripsi = varchar("deskripsi", 128)
    val tanggal = date("tanggal")
    val penyelenggara = varchar("penyelenggara", 64)
    val kategori = varchar("kategori", 64)
    val kategoriHadiah = integer("kategori_hadiah")
    val image = varchar("image", 64)
    val statusLomba = integer("status_lomba")

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(uid)
}