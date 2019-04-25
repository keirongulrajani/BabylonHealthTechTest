package com.keiron.techtest.schedulers

import com.keiron.library.common.schedulers.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import javax.inject.Inject

class SchedulersProviderImpl @Inject constructor() : SchedulersProvider {

    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun current(): Scheduler {
        return CURRENT
    }

    companion object {

        private val CURRENT = Schedulers.from(CurrentThreadExecutor())
    }

    private class CurrentThreadExecutor : Executor {
        override fun execute(command: Runnable) {
            command.run()
        }
    }
}