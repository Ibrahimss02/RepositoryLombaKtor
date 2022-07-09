package com.drunken.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.*

data class LombaResponse(
    @field:SerializedName("id_lomba")
    val idLomba: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("tanggal")
    val tanggal: LocalDate,

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

    @field:SerializedName("history_lomba")
    val historyLomba: List<HistoryLombaResponse>
)
