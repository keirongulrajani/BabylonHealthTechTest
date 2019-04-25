package com.keiron.techtest.application

import androidx.multidex.MultiDexApplication
import com.keiron.techtest.di.ApplicationComponentHolder

class BabylonHealthTechTestApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        ApplicationComponentHolder.create(ApplicationComponentHolder.Factory(this))
    }
}