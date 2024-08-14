package com.codelytical.muvyz.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codelytical.muvyz.core.data.network.MovieApi
import com.codelytical.muvyz.home.domain.model.Series
import retrofit2.HttpException
import java.io.IOException

class TopRatedSeriesSource(private val api: MovieApi) :
    PagingSource<Int, Series>() {
    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {
        return try {
            val nextPage = params.key ?: 1
            val topRatedSeries = api.getTopRatedTvSeries(nextPage)
            LoadResult.Page(
                data = topRatedSeries.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (topRatedSeries.results.isEmpty()) null else topRatedSeries.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
