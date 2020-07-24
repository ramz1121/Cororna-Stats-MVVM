package com.kotlin.corornastats.mvvm

import android.app.Application
import com.kotlin.corornastats.mvvm.di.component.ApplicationComponent
import com.kotlin.corornastats.mvvm.di.component.DaggerApplicationComponent
import com.kotlin.corornastats.mvvm.di.module.ApplicationModule

class CoronaStatsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}