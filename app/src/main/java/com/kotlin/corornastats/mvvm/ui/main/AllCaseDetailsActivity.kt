package com.kotlin.corornastats.mvvm.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.kotlin.corornastats.mvvm.R
import com.kotlin.corornastats.mvvm.di.component.ActivityComponent
import com.kotlin.corornastats.mvvm.ui.base.BaseActivity
import kotlinx.android.synthetic.main.all_case_details.*

class AllCaseDetailsActivity : BaseActivity<AllCaseDetailsViewModel>() {
    override fun provideLayoutId(): Int = R.layout.all_case_details

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        viewModel.onNetworkCall()
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.confirmedCases.observe(this, Observer {
            tv_currentcases.setText(it)
        })
        viewModel.deaths.observe(this, Observer {
            tv_deaths.setText(it)
        })
        viewModel.recoveredCases.observe(this, Observer {
            tv_recoveredcases.setText(it)
        })
        viewModel.totalActive.observe(this, Observer {
            tv_active_cases.setText(it)
        })
        viewModel.totalClosed.observe(this, Observer {
            tv_closed_cases.setText(it)
        })
        viewModel.loggingIn.observe(this, Observer {
            pb_loading.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}