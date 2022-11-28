package com.example.tp1_appkotlin

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@HiltAndroidApp
class MyApplication: Application()

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTmdbApi() : TmdbAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(TmdbAPI::class.java)
    }
    @Singleton
    @Provides
    fun provideRepository(api: TmdbAPI) = Repository(api)
}