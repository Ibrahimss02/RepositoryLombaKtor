package com.drunken.model

import com.google.gson.annotations.SerializedName

data class HistoryLombaBody(
    @field:SerializedName("id_lomba")
    val idLomba: String,

    @field:SerializedName("id_kelompok")
    val idKelompok: String,

    @field:SerializedName("tanggal")
    val tanggal: String,

    @field:SerializedName("hasil")
    val hasil: String,

    @field:SerializedName("link_submission")
    val linkSubmission: String
)
