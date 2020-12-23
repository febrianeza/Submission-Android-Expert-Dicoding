package com.ezafebrian.submissionexpert

import android.app.Application
import com.ezafebrian.core.di.databaseModule
import com.ezafebrian.core.di.networkModule
import com.ezafebrian.core.di.repositoryModule
import com.ezafebrian.submissionexpert.di.useCaseModule
import com.ezafebrian.submissionexpert.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}