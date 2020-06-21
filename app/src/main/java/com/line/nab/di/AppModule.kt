package com.line.nab.di

import android.app.Application
import androidx.room.Room
import com.line.nab.BuildConfig
import com.line.nab.api.WeatherServer
import com.line.nab.db.AppDB
import com.line.nab.db.WeatherDao
import com.line.nab.utilities.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun  provideWeatherService () : WeatherServer {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(WeatherServer::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherDao(db: AppDB): WeatherDao {
        return db.weatherDao()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDB {
        return Room
            .databaseBuilder(app, AppDB::class.java, "App.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}