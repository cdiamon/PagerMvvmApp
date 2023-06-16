package com.task2.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.task2.data.remote.RecipesApi
import com.task2.data.remote.RecipesDataSource
import com.task2.data.remote.RecipesDataStore
import com.task2.domain.repository.DataRepository
import com.task2.domain.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsDataStore(repository: RecipesDataSource): RecipesDataStore

    @Binds
    abstract fun bindsDataRepository(repository: DataRepositoryImpl): DataRepository

    companion object {
        @Provides
        @Singleton
        fun provideRecipesApi(retrofit: Retrofit): RecipesApi {
            return retrofit.create(RecipesApi::class.java)
        }

        @Provides
        fun provideSerializer(): Converter.Factory {
            val contentType = "application/json".toMediaType()
            val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            return json.asConverterFactory(contentType)
        }

        @Provides
        fun provideHttpLogger(): HttpLoggingInterceptor.Logger {
            Timber.plant(Timber.DebugTree())
            return HttpLoggingInterceptor.Logger { message ->
                Timber.d("HTTP::Service:: $message")
            }
        }

        @Provides
        fun provideLoggingInterceptor(
            httpLogger: HttpLoggingInterceptor.Logger
        ): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor(httpLogger)
            loggingInterceptor.level =
                HttpLoggingInterceptor.Level.BODY
            return loggingInterceptor
        }

        @Provides
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient, serializer: Converter.Factory): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(serializer)
                .build()
        }

        private const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/"
    }
}
