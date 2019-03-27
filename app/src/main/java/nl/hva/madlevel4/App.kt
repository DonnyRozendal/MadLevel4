package nl.hva.madlevel4

import android.app.Application
import nl.hva.madlevel4.core.di.koinModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, koinModules)
    }

}