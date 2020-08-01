package com.kotlin.corornastats.mvvm.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kotlin.corornastats.mvvm.R
import com.kotlin.corornastats.mvvm.data.model.Cases
import com.kotlin.corornastats.mvvm.data.remote.NetworkService
import com.kotlin.corornastats.mvvm.data.respository.UserRepository
import com.kotlin.corornastats.mvvm.ui.main.AllCaseDetailsViewModel
import com.kotlin.corornastats.mvvm.utils.common.Resource
import com.kotlin.corornastats.mvvm.utils.network.NetworkHelper
import com.kotlin.corornastats.mvvm.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class AllCaseDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkService: NetworkService

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var loggingInObserver: Observer<Boolean>

    @Mock
    private lateinit var liveDataUserRepository: Observer<Cases>

    @Mock
    private lateinit var messageStringIdObserver: Observer<Resource<Int>>

    private lateinit var testScheduler: TestScheduler

    private lateinit var allCaseDetailsViewModel: AllCaseDetailsViewModel

    @Before
    fun setUp() {

        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testScheduler = TestSchedulerProvider(testScheduler)
        allCaseDetailsViewModel = AllCaseDetailsViewModel(
            testScheduler, compositeDisposable, networkHelper, userRepository
        )
        allCaseDetailsViewModel.loggingIn.observeForever(loggingInObserver)
        allCaseDetailsViewModel.messageStringId.observeForever(messageStringIdObserver)
        // allCaseDetailsViewModel.liveData.observeForever(liveDataUserRepository)

    }

    @Test
    fun givenServerResponse200_whenCaseDetails_shouldLaunchAllcaseDetailsActivity() {
        val casesDetails = Cases("12133322", "13131313", "232323", "21212121", "2323222")
        val cases = MutableLiveData<Cases>()
        cases.postValue(casesDetails)
        doReturn(true)
            .`when`(networkHelper)
            .isNetworkConnected()
        doReturn(Single.just(cases))
            .`when`(userRepository).getCaseNumbers()
        allCaseDetailsViewModel.onNetworkCall()
        testScheduler.triggerActions()
        userRepository.getCaseNumbers()

        assert(allCaseDetailsViewModel.loggingIn.value == false)
        assert(allCaseDetailsViewModel.liveData.value == casesDetails)

        verify(loggingInObserver).onChanged(true)
        verify(loggingInObserver).onChanged(false)

    }

    @Test
    fun givenNoInternet_WhenNetworkCall_shouldShowNetworkError() {
        val casesDetails = Cases("12133322", "13131313", "232323", "21212121", "2323222")
        val cases = MutableLiveData<Cases>()
        cases.postValue(casesDetails)
        doReturn(false)
            .`when`(networkHelper)
            .isNetworkConnected()
        allCaseDetailsViewModel.onNetworkCall()
        assert(allCaseDetailsViewModel.messageStringId.value == Resource.error(R.string.network_connection_error))
        verify(messageStringIdObserver).onChanged(Resource.error(R.string.network_connection_error))
    }

    @After
    fun tearDown() {
        allCaseDetailsViewModel.loggingIn.removeObserver(loggingInObserver)
        allCaseDetailsViewModel.messageStringId.removeObserver(messageStringIdObserver)
//        allCaseDetailsViewModel.liveData.removeObserver(liveDataUserRepository)
    }
}