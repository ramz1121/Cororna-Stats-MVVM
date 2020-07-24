package com.kotlin.corornastats.mvvm.ui.main

import androidx.lifecycle.MutableLiveData
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

    val confirmedCases: MutableLiveData<String> = MutableLiveData()
    val recoveredCases: MutableLiveData<String> = MutableLiveData()
    val deaths: MutableLiveData<String> = MutableLiveData()
    val totalActive: MutableLiveData<String> = MutableLiveData()
    val totalClosed: MutableLiveData<String> = MutableLiveData()


    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()

    fun onNetworkCall() {
        if (checkInternetConncetionWithMessage()) {
            loggingIn.postValue(true)
            val user = userRepository.getCaseNumbers()
            confirmedCases.postValue(user.confirmed)
            recoveredCases.postValue(user.recovered)
            deaths.postValue(user.deaths)
            totalActive.postValue(user.totalActive)
            totalClosed.postValue(user.totalClosed)
            loggingIn.postValue(false)
        } else {
            handleNetworkError(Throwable("Network Error"))
            loggingIn.postValue(false)
        }
    }

    override fun onCreate() {

    }
}