package com.drunken.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class HistoryLombaKelompokResponse(
    @field:SerializedName("id_lomba")
    val id: String,

    @field:SerializedName("nama_lomba")
    val namaLomba: String,

    @field:SerializedName("tahun")
    val tahun: LocalDate,

    @field:SerializedName("hasil")
    val hasil: String
)
