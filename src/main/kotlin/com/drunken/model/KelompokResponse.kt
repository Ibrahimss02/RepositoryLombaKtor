package com.drunken.model

import com.google.gson.annotations.SerializedName

data class KelompokResponse(
    @field:SerializedName("id_kelompok")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("daftar_anggota")
    val daftarAnggota: List<UserResponseLite>,

    @field:SerializedName("history_lomba")
    val historyLomba: List<HistoryLombaKelompokResponse>
)
