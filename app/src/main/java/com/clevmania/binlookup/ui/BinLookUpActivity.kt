package com.clevmania.binlookup.ui

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doOnTextChanged
import com.clevmania.binlookup.MyApp
import com.clevmania.binlookup.R
import com.clevmania.binlookup.model.CardBinModel
import com.clevmania.binlookup.utils.CardFormatter
import com.clevmania.binlookup.utils.EventObserver
import com.clevmania.binlookup.utils.afterTextChanged
import com.clevmania.binlookup.utils.toggleProgress
import kotlinx.android.synthetic.main.activity_bin_lookup.*
import javax.inject.Inject

class BinLookUpActivity : AppCompatActivity() {
    @Inject
    lateinit var binViewModel: BinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bin_lookup)

        etCardNumber.addTextChangedListener(CardFormatter())

        etCardNumber.afterTextChanged {number ->
            if(number.length >= 7){
                binViewModel.binLookup(number.replace(" ",""))
            }
            if(number.length < 5) clear()
        }

        with(binViewModel){
            progress.observe(this@BinLookUpActivity, EventObserver {
                lookUpProgress.toggleProgress(it)
            })

            error.observe(this@BinLookUpActivity, EventObserver {
                showErrorDialog(it)
            })

            cardDetails.observe(this@BinLookUpActivity, EventObserver {
                populateViews(it)
            })
        }
    }

    private fun populateViews(cardDetails : CardBinModel){
        clear()
        tvCardScheme.text = cardDetails.scheme
        tvCardType.text = cardDetails.type
        tvBank.text = cardDetails.bank.name
        tvCountry.text = getString(R.string.card_country,
            cardDetails.country.emoji, cardDetails.country.name)
        tvCardLength.text = cardLength(cardDetails.number.length)
        tvPrepaidPostPaid.text = cardPlan(cardDetails.prepaid)
    }

    private fun cardLength(length: Int): String?{
        if (length == 0) return null
        return length.toString()
    }

    private fun cardPlan(isPrepaid: Boolean): String {
        return if (isPrepaid){
            getString(R.string.prepaid)
        }else {
            getString(R.string.postpaid)
        }
    }

    private fun clear(){
        tvCardScheme.text = null
        tvCardType.text = null
        tvBank.text = null
        tvCountry.text = null
        tvCardLength.text = null
        tvPrepaidPostPaid.text = null
    }

    private fun showErrorDialog(message: String, title: String = getString(R.string.error_title)){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        builder.create().show()
    }
}