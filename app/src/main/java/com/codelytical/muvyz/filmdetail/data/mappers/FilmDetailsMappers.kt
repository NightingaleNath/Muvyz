
package com.codelytical.muvyz.filmdetail.data.mappers

import com.codelytical.muvyz.cast.data.network.CastResponse
import com.codelytical.muvyz.cast.domain.model.Cast
import com.codelytical.muvyz.cast.domain.model.Credits
import com.codelytical.muvyz.home.data.network.CreditsResponse

internal fun CreditsResponse.toDomain() = Credits(
    id = id,
    cast = cast.map { it.toDomain() }
)

internal fun CastResponse.toDomain() = Cast(
    adult = adult,
    castId = castId,
    character = character,
    creditId = creditId,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    order = order,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath,
)
