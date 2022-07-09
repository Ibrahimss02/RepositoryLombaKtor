package com.drunken.model

import com.google.gson.annotations.SerializedName

data class UserResponseLite(
    @field:SerializedName("id")
    val idUser: String,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("jabatan")
    val jabatan: String,

    @field:SerializedName("image")
    val image: String,
)
