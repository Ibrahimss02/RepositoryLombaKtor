package com.drunken.data.table

import org.jetbrains.exposed.sql.Table

object UserTable: Table() {
    override val tableName: String
        get() = "user"

    val uid = varchar("id_user", 128)
    val name = varchar("name", 64)
    val prodi = varchar("prodi", 64)
    val angkatan = integer("angkatan")
    val jabatan = varchar("jabatan", 64)
    val image = varchar("image", 128)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(uid)
}