package com.example.fit4you_android

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.fit4you_android.util.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Fit4YouApp:Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        prefs = PreferenceUtil(applicationContext)
    }
}