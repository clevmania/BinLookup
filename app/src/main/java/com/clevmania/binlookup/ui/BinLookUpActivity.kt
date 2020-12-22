package com.clevmania.binlookup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clevmania.binlookup.R
import com.clevmania.binlookup.utils.CardFormatter
import com.clevmania.binlookup.utils.afterTextChanged
import kotlinx.android.synthetic.main.activity_bin_lookup.*
import javax.inject.Inject

class BinLookUpActivity : AppCompatActivity() {
    @Inject
    lateinit var binViewModel: BinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bin_lookup)

        etCardNumber.addTextChangedListener(CardFormatter())

        etCardNumber.afterTextChanged {number ->
            if(number.length >= 7){
                binViewModel.binLookup(number.replace(" ",""))
            }
        }
    }
}