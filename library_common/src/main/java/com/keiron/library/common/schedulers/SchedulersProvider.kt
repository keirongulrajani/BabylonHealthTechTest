package com.keiron.library.common.schedulers

import io.reactivex.Scheduler

interface SchedulersProvider {

    fun mainThread(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

    fun current(): Scheduler
}
