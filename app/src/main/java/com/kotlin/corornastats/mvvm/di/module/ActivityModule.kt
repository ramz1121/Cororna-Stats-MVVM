package com.kotlin.corornastats.mvvm.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.corornastats.mvvm.data.respository.UserRepository
import com.kotlin.corornastats.mvvm.ui.base.BaseActivity
import com.kotlin.corornastats.mvvm.ui.main.AllCaseDetailsViewModel
import com.kotlin.corornastats.mvvm.utils.ViewModelProviderFactory
import com.kotlin.corornastats.mvvm.utils.network.NetworkHelper
import com.kotlin.corornastats.mvvm.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private var activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideAllCaseDetailsViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): AllCaseDetailsViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(AllCaseDetailsViewModel::class) {
            AllCaseDetailsViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        }).get(AllCaseDetailsViewModel::class.java)
}