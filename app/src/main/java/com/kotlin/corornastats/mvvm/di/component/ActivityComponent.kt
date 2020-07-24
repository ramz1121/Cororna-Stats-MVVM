package com.kotlin.corornastats.mvvm.di.component

import com.kotlin.corornastats.mvvm.di.ActivityScope
import com.kotlin.corornastats.mvvm.di.module.ActivityModule
import com.kotlin.corornastats.mvvm.ui.main.AllCaseDetailsActivity
import com.kotlin.corornastats.mvvm.ui.splash.SplashActivity
import dagger.Component


@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: AllCaseDetailsActivity)


}