package shvyn22.weatherapplication.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shvyn22.weatherapplication.api.ApiInterface
import shvyn22.weatherapplication.data.local.AppDatabase
import shvyn22.weatherapplication.data.local.dao.LocationDao
import shvyn22.weatherapplication.repository.Repository
import shvyn22.weatherapplication.repository.RepositoryImpl
import shvyn22.weatherapplication.util.Constants.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "app_database")
            .build()

    @Singleton
    @Provides
    fun provideLocationDao(db: AppDatabase): LocationDao =
        db.locationDao()

    @Singleton
    @Provides
    fun provideRepository(
        api: ApiInterface,
        locationDao: LocationDao
    ): Repository = RepositoryImpl(api, locationDao)

    @Singleton
    @Provides
    fun provideDataStore(app: Application): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                app.preferencesDataStoreFile("preferences")
            }
        )
}