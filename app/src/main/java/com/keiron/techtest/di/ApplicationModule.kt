package com.keiron.techtest.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.keiron.library.common.schedulers.SchedulersProvider
import com.keiron.techtest.application.BabylonHealthTechTestApplication
import com.keiron.techtest.schedulers.SchedulersProviderImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideApplication(application: Application): BabylonHealthTechTestApplication =
        application as BabylonHealthTechTestApplication

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources = context.resources

    @Provides
    @Reusable
    fun providesSchedulers(schedulersProviderImpl: SchedulersProviderImpl): SchedulersProvider = schedulersProviderImpl
}