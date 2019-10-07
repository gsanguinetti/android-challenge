package es.npatarino.android.gotchallenge

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GotChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GotChallengeApplication)
            androidLogger(Level.DEBUG)
            modules(appModules)
        }
        if(BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }
}