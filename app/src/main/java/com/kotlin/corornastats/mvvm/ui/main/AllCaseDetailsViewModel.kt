package com.kotlin.corornastats.mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kotlin.corornastats.mvvm.data.model.Cases
import com.kotlin.corornastats.mvvm.data.respository.UserRepository
import com.kotlin.corornastats.mvvm.ui.base.BaseViewModel
import com.kotlin.corornastats.mvvm.utils.network.NetworkHelper
import com.kotlin.corornastats.mvvm.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class AllCaseDetailsViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    var liveData: MutableLiveData<Cases> = userRepository.liveData
    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()

    fun onNetworkCall() {
        if (checkInternetConncetionWithMessage()) {
            loggingIn.postValue(true)
            compositeDisposable.addAll(
                userRepository.getCaseNumbers()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe({
                        liveData = it
                        loggingIn.postValue(false)

                    }, {
                        handleNetworkError(it)
                        loggingIn.postValue(false)
                    }
                    )
            )
        }
    }

    override fun onCreate() {

    }
}