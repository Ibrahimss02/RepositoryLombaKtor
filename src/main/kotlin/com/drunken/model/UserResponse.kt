package com.drunken.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("id")
    val idUser: String,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("prodi")
    val prodi: String,

    @field:SerializedName("angkatan")
    val angkatan: Int,

    @field:SerializedName("jabatan")
    val jabatan: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("history_lomba")
    val historyLomba: List<HistoryLombaResponse>
)