package com.codelytical.muvyz.cast.domain.repository

import com.codelytical.muvyz.cast.domain.model.Credits
import com.codelytical.muvyz.core.util.Resource

interface CastRepository {
    suspend fun getTvSeriesCasts(id: Int): Resource<Credits>
    suspend fun getMovieCasts(id: Int): Resource<Credits>
    suspend fun getCastDetails(id: Int): Resource<Unit>
}
