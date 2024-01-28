package com.org.test.geminisample.di.module

import com.org.test.geminisample.remote.rest.api.RestApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule(private val baseURL: String) {

    @Provides
    @Reusable
    fun providesRetrofit(): RestApi {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestApi::class.java)
    }

}