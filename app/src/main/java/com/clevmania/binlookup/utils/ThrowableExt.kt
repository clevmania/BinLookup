package com.clevmania.binlookup.utils

import com.google.gson.JsonParser
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */
fun Throwable.toErrorMessage(): String {
    return when (this) {
        is HttpException -> {
            return when (code()) {
                in 500..511 -> {
                    Constants.SERVER_ERROR
                }
                404 -> {
                    Constants.NO_MATCHING_CARD
                }
                429  -> {
                    Constants.TOO_MANY_REQUEST
                }
                else -> {
                    return try {
                        val errorJsonString = response()?.errorBody()?.string()
                        JsonParser().parse(errorJsonString)
                            .asJsonObject["message"]
                            .asString
                    } catch (e: Exception) {
                        Constants.CLIENT_ERROR
                    }
                }
            }
        }
        is SocketTimeoutException -> {
            return Constants.SOCKET_TIMEOUT_ERROR
        }
        is UnknownHostException ->{
            return Constants.CONNECTION_ERROR
        }
        is ConnectException -> {
            return Constants.CONNECTION_ERROR
        }
        is IOException -> {
            return Constants.IO_ERROR
        }
        else -> {
            "Unable to process request, ${this.localizedMessage}"
        }
    }
}