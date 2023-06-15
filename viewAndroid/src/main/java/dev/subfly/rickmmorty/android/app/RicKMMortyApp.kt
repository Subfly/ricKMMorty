package dev.subfly.rickmmorty.android.app

import android.app.Application
import dev.subfly.rickmmorty.di.DIHelper
import dev.subfly.rickmmorty.android.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RicKMMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DIHelper.initKoin {
            androidLogger()
            androidContext(this@RicKMMortyApp)
            modules(viewModelsModule)
        }
    }

}