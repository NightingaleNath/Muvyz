package com.codelytical.muvyz.cast.domain.usecase

import com.codelytical.muvyz.cast.domain.repository.CastRepository
import javax.inject.Inject

class GetTvCastUseCase @Inject constructor(
    private val repository: CastRepository,
) {
    suspend operator fun invoke(id: Int) = repository.getTvSeriesCasts(id)
}
