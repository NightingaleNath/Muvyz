package com.codelytical.muvyz.core.data.network

import com.codelytical.muvyz.BuildConfig.API_KEY
import com.codelytical.muvyz.core.util.Constants.STARTING_PAGE_INDEX
import com.codelytical.muvyz.genre.data.network.GenresResponse
import com.codelytical.muvyz.home.data.network.CreditsResponse
import com.codelytical.muvyz.home.data.network.MovieDetails
import com.codelytical.muvyz.home.data.network.MoviesResponse
import com.codelytical.muvyz.home.data.network.TvSeriesDetails
import com.codelytical.muvyz.home.data.network.TvSeriesResponse
import com.codelytical.muvyz.search.data.network.MultiSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("trending/movie/day")
    suspend fun getTrendingTodayMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MoviesResponse

    @GET("trending/tv/day")
    suspend fun getTrendingTvSeries(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): TvSeriesResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTvSeries(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): TvSeriesResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvSeries(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): TvSeriesResponse

    @GET("tv/popular")
    suspend fun getPopularTvSeries(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): TvSeriesResponse

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvSeries(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): TvSeriesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MovieDetails

    @GET("tv/{tv_id}")
    suspend fun getTvSeriesDetails(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): TvSeriesDetails

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): CreditsResponse

    @GET("tv/{tv_id}/credits")
    suspend fun getTvSeriesCredits(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): CreditsResponse

    @GET("credit/{credit_id}")
    suspend fun getCreditDetails(
        @Path("credit_id") creditId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    )

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): GenresResponse

    @GET("genre/tv/list")
    suspend fun getTvSeriesGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): GenresResponse

    @GET("search/multi")
    suspend fun multiSearch(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY
    ): MultiSearchResponse
}
