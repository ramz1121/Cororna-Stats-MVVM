package com.kotlin.corornastats.mvvm

import android.content.Context
import com.kotlin.corornastats.mvvm.di.component.DaggerTestComponent
import com.kotlin.corornastats.mvvm.di.component.TestComponent
import com.kotlin.corornastats.mvvm.di.module.ApplicationTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context: Context) : TestRule {

    private var testComponent: TestComponent? = null

    fun getContext() = context

    private fun setupDaggerTestComponentInApplication() {
        val application = context.applicationContext as CoronaStatsApplication
        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .build()
        application.setComponent(testComponent!!)
    }

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    setupDaggerTestComponentInApplication()
                    base.evaluate()
                } finally {
                    testComponent = null
                }
            }
        }
    }
}