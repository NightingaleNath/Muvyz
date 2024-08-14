package com.codelytical.muvyz.search.domain.repository

import androidx.paging.PagingData
import com.codelytical.muvyz.search.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun multiSearch(queryParam: String): Flow<PagingData<Search>>
}
