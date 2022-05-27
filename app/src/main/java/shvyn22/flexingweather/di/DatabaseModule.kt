package shvyn22.flexingweather.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.flexingweather.data.local.AppDatabase
import shvyn22.flexingweather.data.local.dao.LocationDao
import shvyn22.flexingweather.util.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase =
        Room
            .databaseBuilder(
                app,
                AppDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideLocationDao(db: AppDatabase): LocationDao =
        db.locationDao()
}