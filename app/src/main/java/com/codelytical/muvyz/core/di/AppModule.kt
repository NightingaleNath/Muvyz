package com.codelytical.muvyz.core.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.codelytical.muvyz.core.data.network.MovieApi
import com.codelytical.muvyz.core.data.repository.PreferenceRepositoryImpl
import com.codelytical.muvyz.core.domain.repository.PreferenceRepository
import com.codelytical.muvyz.core.util.Constants
import com.codelytical.muvyz.core.util.Constants.BASE_URL
import com.codelytical.muvyz.core.util.Constants.DATABASE_NAME
import com.codelytical.muvyz.favorites.data.data.local.FavoritesDatabase
import com.codelytical.muvyz.filmdetail.data.repository.FilmsDetailsRepository
import com.codelytical.muvyz.home.data.repository.MoviesRepository
import com.codelytical.muvyz.home.data.repository.TvSeriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providesTMDBApi(okHttpClient: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoritesDatabase(application: Application): FavoritesDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoritesDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFilmsDetailsRepository(api: MovieApi) = FilmsDetailsRepository(api)

    @Provides
    @Singleton
    fun provideMoviesRepository(api: MovieApi) = MoviesRepository(api)

    @Provides
    @Singleton
    fun provideTvSeriesRepository(api: MovieApi) = TvSeriesRepository(api)


    @Provides
    @Singleton
    fun provideDatastorePreferences(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(Constants.MOVIE_PREFERENCES)
            }
        )

    @Provides
    @Singleton
    fun providePreferenceRepository(dataStore: DataStore<Preferences>): PreferenceRepository =
        PreferenceRepositoryImpl(dataStore)
}
