package com.sdimosikvip.weather.di

import android.app.Activity
import android.app.Application
import com.sdimosikvip.weather.MainApp
import com.sdimosikvip.weather.base.BaseFragment
import com.sdimosikvip.weather.di.module.AppModule
import com.sdimosikvip.weather.di.module.ViewModelBindModule
import com.sdimosikvip.weather.screens.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelBindModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: BaseFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
