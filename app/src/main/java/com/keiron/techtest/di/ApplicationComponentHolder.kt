package com.keiron.techtest.di

import android.app.Application
import com.keiron.library.common.di.ComponentHolder

object ApplicationComponentHolder : ComponentHolder<ApplicationComponent, ApplicationComponentHolder.Factory>() {

    override fun getComponentClass() = ApplicationComponent::class.java

    open class Factory(
        private val application: Application
    ) : ComponentHolder.Factory<ApplicationComponent>() {

        override fun build(): ApplicationComponent {
            return DaggerApplicationComponent.builder()
                .application(application)
                .build()
        }
    }
}