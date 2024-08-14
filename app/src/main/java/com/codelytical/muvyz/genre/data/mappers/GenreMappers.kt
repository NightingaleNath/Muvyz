
package com.codelytical.muvyz.genre.data.mappers

import com.codelytical.muvyz.genre.data.network.GenresResponse
import com.codelytical.muvyz.genre.domain.model.Genre

internal fun GenresResponse.GenreDto.toDomain() = Genre(
    id = id,
    name = name
)
