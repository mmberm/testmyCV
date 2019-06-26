package com.antoniunix.data.network

import com.antoniunix.data.services.CVServices
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class Networkmodule(val baseUrl: String) {

    @Provides
    @Singleton
    fun providerRetrofit(gson: Gson,
                         okHttpClient: OkHttpClient,
                         rx2AdapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rx2AdapterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun cvServicesApiService(retrofit: Retrofit): CVServices {
        return retrofit.create(CVServices::class.java)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(stethoInterceptor: StethoInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.retryOnConnectionFailure(false)
        builder.connectTimeout(TIME_OUT_API.toLong(), TimeUnit.SECONDS)
        builder.readTimeout(TIME_OUT_API.toLong(), TimeUnit.SECONDS)
        builder.writeTimeout(TIME_OUT_API.toLong(), TimeUnit.SECONDS)
        builder.networkInterceptors().add(stethoInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesStethoInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }


    @Provides
    @Singleton
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    companion object {
        const val TIME_OUT_API = 30 // seconds
    }
}