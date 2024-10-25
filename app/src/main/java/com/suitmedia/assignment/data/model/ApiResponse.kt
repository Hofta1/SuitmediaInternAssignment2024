package com.suitmedia.assignment.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    @SerializedName("total_pages") val total_pages: Int,
    val data: MutableList<Profile>
)
