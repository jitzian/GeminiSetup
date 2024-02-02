package com.org.test.geminisample.di.module

import com.org.test.geminisample.remote.rest.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RemoteModule(private val baseURL: String) {

    @Provides
    @Reusable
    fun providesRetrofit(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}