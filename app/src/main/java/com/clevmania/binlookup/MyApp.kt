package com.clevmania.binlookup

import android.app.Application
import com.clevmania.binlookup.di.AppComponent
import com.clevmania.binlookup.di.DaggerAppComponent

/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */
class MyApp : Application() {
    val appComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}