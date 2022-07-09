package com.drunken.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.*

data class HistoryLombaResponse(
    @field:SerializedName("id_kelompok")
    val idKelompok: String,

    @field:SerializedName("nama_kelompok")
    val namaKelompok: String,

    @field:SerializedName("tahun")
    val tahun: LocalDate,

    @field:SerializedName("hasil")
    val hasil: String,

    @field:SerializedName("link_submission")
    val linkSubmission: String
)
