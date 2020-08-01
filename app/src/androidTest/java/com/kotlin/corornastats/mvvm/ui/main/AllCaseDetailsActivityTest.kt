package com.kotlin.corornastats.mvvm.ui.main

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.kotlin.corornastats.mvvm.R
import com.kotlin.corornastats.mvvm.TestComponentRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class AllCaseDetailsActivityTest {
    private val component =
        TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val main = IntentsTestRule(AllCaseDetailsActivity::class.java, false, false)

    @get:Rule
    val chain = RuleChain.outerRule(component).around(main)

    @Before
    fun setUp() {

    }

    @Test
    fun testCheckViewsDisplay() {
        main.launchActivity(Intent(component.getContext(), AllCaseDetailsActivity::class.java))

        onView(withId(R.id.tv_currentcases))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_deaths))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_recoveredcases))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_active_cases))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_closed_cases))
            .check(matches(isDisplayed()))
    }


}