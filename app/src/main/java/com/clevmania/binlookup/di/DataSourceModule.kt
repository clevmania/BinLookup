package com.clevmania.binlookup.di

import com.clevmania.binlookup.data.CardBinDataSource
import com.clevmania.binlookup.data.CardBinRepository
import dagger.Binds
import dagger.Module

/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */
@Module
abstract class DataSourceModule{
    @Binds
    abstract fun provideBinDataSource(source: CardBinRepository): CardBinDataSource
}