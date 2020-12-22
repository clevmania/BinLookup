package com.clevmania.binlookup.data

import com.clevmania.binlookup.model.CardBinModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */
interface CardBinDataService {
    @GET("/{cardNumber}")
    suspend fun lookup(
        @Path("cardNumber") cardNo: String
    ): CardBinModel
}

interface CardBinDataSource {
    suspend fun lookup(cardNumber: String): CardBinModel
}