
package com.codelytical.muvyz.home.data.network

import com.codelytical.muvyz.home.domain.model.Series
import com.google.gson.annotations.SerializedName

data class TvSeriesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Series>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)
