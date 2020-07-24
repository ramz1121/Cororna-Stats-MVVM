package com.kotlin.corornastats.mvvm.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.kotlin.corornastats.mvvm.BuildConfig
import com.kotlin.corornastats.mvvm.CoronaStatsApplication
import com.kotlin.corornastats.mvvm.data.local.db.DatabaseService
import com.kotlin.corornastats.mvvm.data.remote.NetworkService
import com.kotlin.corornastats.mvvm.data.remote.Networking
import com.kotlin.corornastats.mvvm.di.ApplicationContext
import com.kotlin.corornastats.mvvm.utils.network.NetworkHelper
import com.kotlin.corornastats.mvvm.utils.rx.RxSchedulerProvider
import com.kotlin.corornastats.mvvm.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: CoronaStatsApplication){

    @Provides
    @Singleton
    fun provideApplication(): Application =application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context =application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("corona-stats-project-prefs", Context.MODE_PRIVATE)

    /**
     * We need to write @Singleton on the provide method if we are create the instance inside this method
     * to make it singleton. Even if we have written @Singleton on the instance's class
     */
    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "corona-stats-project-db"
        ).build()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

}