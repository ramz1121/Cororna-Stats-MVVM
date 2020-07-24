package com.kotlin.corornastats.mvvm.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext