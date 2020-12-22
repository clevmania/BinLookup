package com.clevmania.binlookup.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar

/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */

fun View.makeVisible(){ this.visibility = View.VISIBLE
}

fun View.makeGone(){ this.visibility = View.GONE
}

fun View.makeInVisible() { this.visibility = View.INVISIBLE}

fun ProgressBar.toggleProgress(it: Boolean) {
    if(it){ this.makeVisible() }
    else{ this.makeGone() }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}