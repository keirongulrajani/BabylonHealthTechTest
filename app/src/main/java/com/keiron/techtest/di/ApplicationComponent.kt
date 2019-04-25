package com.keiron.techtest.di

import android.app.Application
import com.keiron.data.accounts.di.AccountsModule
import com.keiron.data.posts.di.PostModule
import com.keiron.library.common.network.di.NetworkModule
import com.keiron.techtest.section.details.DetailsFragment
import com.keiron.techtest.section.main.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        AccountsModule::class,
        PostModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(mainFragment: MainFragment)

    fun inject(detailsFragment: DetailsFragment)
}