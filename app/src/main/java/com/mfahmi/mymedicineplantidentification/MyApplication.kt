package com.mfahmi.mymedicineplantidentification

import android.app.Application
import com.mfahmi.mymedicineplantidentification.di.useCaseModule
import com.mfahmi.mymedicineplantidentification.di.viewModelModule
import com.mfahmi.mymedicineplantidentification.domain.di.databaseModule
import com.mfahmi.mymedicineplantidentification.domain.di.networkModule
import com.mfahmi.mymedicineplantidentification.domain.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(databaseModule, networkModule, repositoryModule, useCaseModule, viewModelModule)
            )
        }
    }
}