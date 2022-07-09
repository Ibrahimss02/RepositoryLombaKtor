package com.drunken.model

import com.google.gson.annotations.SerializedName

data class UserBody(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("prodi")
    val prodi: String,

    @field:SerializedName("angkatan")
    val angkatan: Int,

    @field:SerializedName("jabatan")
    val jabatan: String,

    @field:SerializedName("image")
    val image: String
)