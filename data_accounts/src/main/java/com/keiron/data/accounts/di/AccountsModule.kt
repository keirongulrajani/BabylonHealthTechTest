package com.keiron.data.accounts.di

import com.keiron.data.accounts.client.ProfileClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
public class AccountsModule {

    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com"
    }

    @Provides
    fun provideProfileClient(okHttpClient: OkHttpClient): ProfileClient {
        val restAdapter = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .validateEagerly(true)
            .build()

        return restAdapter.create(ProfileClient::class.java)
    }
}