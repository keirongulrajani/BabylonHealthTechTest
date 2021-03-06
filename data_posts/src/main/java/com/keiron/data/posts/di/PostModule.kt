package com.keiron.data.posts.di

import com.google.gson.Gson
import com.keiron.babylonhealth.domain.posts.repository.PostRepository
import com.keiron.data.posts.client.PostClient
import com.keiron.data.posts.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class PostModule {
    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com"
    }

    @Reusable
    @Provides
    fun providePostClient(okHttpClient: OkHttpClient, gson: Gson): PostClient {
        val restAdapter = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .validateEagerly(true)
            .build()

        return restAdapter.create(PostClient::class.java)
    }

    @Provides
    fun providePostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository {
        return postRepositoryImpl
    }
}