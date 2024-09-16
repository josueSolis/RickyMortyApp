package com.omni.pro.characters.android

import android.app.Application
import com.omni.pro.characters.android.di.appModule
import com.omni.pro.characters.di.apolloModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CharactersApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CharactersApp)
            modules(apolloModule, appModule)
        }
    }
}