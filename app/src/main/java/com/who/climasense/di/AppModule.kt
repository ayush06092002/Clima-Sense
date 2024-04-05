package com.who.climasense.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.who.climasense.data.WeatherDatabase
import com.who.climasense.network.WeatherApi
import com.who.climasense.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase)
    = weatherDatabase.weatherDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase
    = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_database"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi {
        // Create an OkHttpClient with an HttpLoggingInterceptor
        val httpClient = OkHttpClient.Builder()

        // Add logging interceptor to log request and response information
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Set log level as desired
        }
        httpClient.addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("your_preference_file_key", Context.MODE_PRIVATE)
    }

}