package com.clevmania.binlookup.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clevmania.binlookup.data.CardBinDataSource
import com.clevmania.binlookup.model.CardBinModel
import com.clevmania.binlookup.utils.EventUtils
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */
class BinViewModel @Inject constructor(
    private val cardBinDataSource: CardBinDataSource
) : ViewModel() {
    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress: LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error: LiveData<EventUtils<String>> = _error

    private val _cardDetails = MutableLiveData<EventUtils<CardBinModel>>()
    val cardDetails : LiveData<EventUtils<CardBinModel>> = _cardDetails

    fun binLookup(cardNo: String){
        viewModelScope.launch {
            _progress.value = EventUtils(true)
            try {
                val response = cardBinDataSource.lookup(cardNo)
                _cardDetails.value = EventUtils(response)
            }catch (ex: Exception){
                _error.value = EventUtils(ex.message!!)
            }finally {
                _progress.value = EventUtils(false)
            }
        }
    }
}