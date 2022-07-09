package com.drunken.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class LombaBody(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("tanggal")
    val tanggal: String,

    @field:SerializedName("penyelenggara")
    val penyelenggara: String,

    @field:SerializedName("kategori")
    val kategori: String,

    @field:SerializedName("kategori_hadiah")
    val kategoriHadiah: Int,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("status_lomba")
    val statusLomba: Int,
)
