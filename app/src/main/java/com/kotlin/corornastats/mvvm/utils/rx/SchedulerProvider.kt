package com.kotlin.corornastats.mvvm.utils.rx

import io.reactivex.Scheduler
import org.jetbrains.annotations.Async

interface SchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}