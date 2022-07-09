package com.drunken.data.table

import org.jetbrains.exposed.sql.Table

object KelompokTable: Table() {
    override val tableName: String
        get() = "kelompok"

    val uid = varchar("id_kelompok", 128)
    val name = varchar("name", 64)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(uid)
}