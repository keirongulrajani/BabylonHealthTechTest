package com.keiron.library.common.network.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        const val TIMEOUT_IN_SECONDS = "timeoutInSeconds"
        private const val TIMEOUT_IN_SEC: Int = 15
    }

    @Provides
    @Named(TIMEOUT_IN_SECONDS)
    fun provideNetworkTimeout(): Int {
        return TIMEOUT_IN_SEC
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        cache: Cache,
        @Named(TIMEOUT_IN_SECONDS)
        networkTimeoutInSeconds: Int
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(networkTimeoutInSeconds.toLong(), TimeUnit.SECONDS)
        client.readTimeout(networkTimeoutInSeconds.toLong(), TimeUnit.SECONDS)
        client.cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideGson() = GsonBuilder().setLenient().create()
}