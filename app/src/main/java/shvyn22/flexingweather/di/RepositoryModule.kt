package shvyn22.flexingweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.flexingweather.data.local.dao.LocationDao
import shvyn22.flexingweather.data.remote.api.ApiService
import shvyn22.flexingweather.repository.Repository
import shvyn22.flexingweather.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        api: ApiService,
        locationDao: LocationDao
    ): Repository = RepositoryImpl(api, locationDao)
}