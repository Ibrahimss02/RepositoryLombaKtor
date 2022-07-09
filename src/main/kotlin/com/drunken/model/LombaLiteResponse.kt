package com.drunken.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class LombaLiteResponse(
    @field:SerializedName("id_lomba")
    val idLomba: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("tanggal")
    val tanggal: LocalDate,

    @field:SerializedName("penyelenggara")
    val penyelenggara: String,

    @field:SerializedName("kategori")
    val kategori: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("status_lomba")
    val statusLomba: Int,
)
