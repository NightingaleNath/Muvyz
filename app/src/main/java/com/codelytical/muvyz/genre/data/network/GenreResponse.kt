
package com.codelytical.muvyz.genre.data.network

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<GenreDto>
) {
    data class GenreDto(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}
