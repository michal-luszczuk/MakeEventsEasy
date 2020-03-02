package com.luszczuk.makeeventseasy.sample.injection

import android.app.Application
import com.luszczuk.makeeventseasy.sample.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TrackerModule::class])
interface MyApplicationComponent {

    fun inject(main: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Application): MyApplicationComponent
    }
}