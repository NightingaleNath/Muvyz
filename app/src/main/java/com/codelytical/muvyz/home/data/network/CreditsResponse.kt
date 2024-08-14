package com.codelytical.muvyz.home.data.network

import com.google.gson.annotations.SerializedName
import com.codelytical.muvyz.cast.data.network.CastResponse

data class CreditsResponse(
    @SerializedName("cast")
    val cast: List<CastResponse>,
    @SerializedName("id")
    val id: Int
)
