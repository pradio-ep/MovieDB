package com.pradioep.test

import android.app.Application
import com.pradioep.test.di.NetworkModule
import com.pradioep.test.di.RepositoryModule
import com.pradioep.test.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    NetworkModule,
                    RepositoryModule,
                    ViewModelModule
                )
            )
        }
    }
}