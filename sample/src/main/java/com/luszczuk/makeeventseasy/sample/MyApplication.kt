package com.luszczuk.makeeventseasy.sample

import android.app.Application
import com.luszczuk.makeeventseasy.sample.injection.DaggerMyApplicationComponent
import com.luszczuk.makeeventseasy.sample.injection.MyApplicationComponent

class MyApplication : Application() {

    lateinit var appComponent: MyApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerMyApplicationComponent.factory().create(this)
    }
}