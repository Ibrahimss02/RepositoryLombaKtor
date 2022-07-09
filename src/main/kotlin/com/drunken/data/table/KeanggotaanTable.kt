package com.drunken.data.table

import org.jetbrains.exposed.sql.Table

object KeanggotaanTable: Table() {
    override val tableName: String
        get() = "keanggotaan"

    val idKelompok = reference("id_kelompok", KelompokTable.uid)
    val idUser = reference("id_user", UserTable.uid)
}