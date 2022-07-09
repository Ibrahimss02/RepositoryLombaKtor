package com.drunken.model

import com.google.gson.annotations.SerializedName

data class KeanggotaanBody(
    @field:SerializedName("id_kelompok")
    val idKelompok: String,

    @field:SerializedName("id_user")
    val idUser: String
)
