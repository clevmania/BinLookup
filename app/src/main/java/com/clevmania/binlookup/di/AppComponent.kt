package com.clevmania.binlookup.di

import android.content.Context
import com.clevmania.binlookup.ui.BinLookUpActivity
import dagger.BindsInstance
import dagger.Component

/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */
@Component(modules = [ServiceModule::class, DataSourceModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: BinLookUpActivity)

}