package com.kotlin.corornastats.mvvm.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {
}