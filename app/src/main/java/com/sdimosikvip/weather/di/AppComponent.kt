package com.sdimosikvip.weather.di

import android.app.Application
import com.sdimosikvip.weather.MainApp
import com.sdimosikvip.weather.di.module.AppModule
import com.sdimosikvip.weather.di.module.ViewModelBindModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelBindModule::class])
interface AppComponent {

    fun inject(application: MainApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
