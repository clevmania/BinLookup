package com.clevmania.binlookup.di

import com.clevmania.binlookup.data.ApiService
import com.clevmania.binlookup.data.CardBinDataService
import dagger.Module
import dagger.Provides

/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */
@Module
class ServiceModule {
    @Provides
    fun provideBinService(): CardBinDataService {
        return ApiService.invoke().create(CardBinDataService::class.java)
    }
}