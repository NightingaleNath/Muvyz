package com.codelytical.muvyz.search.data.network


import com.codelytical.muvyz.search.domain.model.Search
import com.google.gson.annotations.SerializedName

data class MultiSearchResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<Search>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
