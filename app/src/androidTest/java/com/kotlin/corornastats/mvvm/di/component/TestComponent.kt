package com.kotlin.corornastats.mvvm.di.component

import com.kotlin.corornastats.mvvm.di.module.ApplicationTestModule
import com.kotlin.corornastats.mvvm.ui.main.AllCaseDetailsActivity
import com.kotlin.corornastats.mvvm.ui.main.AllCaseDetailsActivityTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent :ApplicationComponent {
}