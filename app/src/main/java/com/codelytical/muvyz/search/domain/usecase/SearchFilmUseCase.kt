package com.codelytical.muvyz.search.domain.usecase

import androidx.paging.PagingData
import androidx.paging.filter
import com.codelytical.muvyz.search.domain.model.Search
import com.codelytical.muvyz.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchFilmUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(searchParam: String): Flow<PagingData<Search>> {
        return repository.multiSearch(searchParam).map { pagingData ->
            pagingData.filter { search ->
                ((search.title != null || search.originalName != null || search.originalTitle != null) &&
                        (search.mediaType == "tv" || search.mediaType == "movie"))
            }
        }
    }
}
