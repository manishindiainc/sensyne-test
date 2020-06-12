package com.sensynetest.app

import android.app.Application
import com.sensynetest.app.framework.AppPreferenceManager

class SensyneTestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AppPreferenceManager.init(this)
    }
}