package com.keiron.data.accounts.di

import com.google.gson.Gson
import com.keiron.babylonhealth.domain.accounts.repository.AccountRepository
import com.keiron.data.accounts.client.ProfileClient
import com.keiron.data.accounts.repository.AccountRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AccountsModule {

    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com"
    }

    @Provides
    @Reusable
    fun provideProfileClient(okHttpClient: OkHttpClient, gson: Gson): ProfileClient {
        val restAdapter = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .validateEagerly(true)
            .build()

        return restAdapter.create(ProfileClient::class.java)
    }

    @Provides
    fun provideAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository {
        return accountRepositoryImpl
    }
}