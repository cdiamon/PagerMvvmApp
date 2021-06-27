package com.example.companyApp.di

import com.example.companyApp.data.remote.CarsDataStore
import com.example.companyApp.data.remote.CarsApi
import com.example.companyApp.data.remote.RemoteCarsDataSource
import com.example.companyApp.repository.DataRepository
import com.example.companyApp.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCarsApi(retrofit: Retrofit): CarsApi {
        return retrofit.create(CarsApi::class.java)
    }

    @Provides
    fun provideCarsDataStore(api: CarsApi): CarsDataStore =
        RemoteCarsDataSource(api)

    @Provides
    fun provideDataRepository(dataSource: RemoteCarsDataSource): DataRepository =
        DataRepositoryImpl(dataSource)
}
