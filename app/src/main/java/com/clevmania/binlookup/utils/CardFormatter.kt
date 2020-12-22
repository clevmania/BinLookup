package com.clevmania.binlookup.utils

import android.text.Editable
import android.text.TextWatcher


/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */

class CardFormatter : TextWatcher {

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {
        var source = s.toString()
        if (lastSource != source) {
            source = source.replace(SPACE, EMPTY_STRING)
            val stringBuilder = StringBuilder()
            for (i in source.indices) {
                if (i > 0 && i % 4 == 0) {
                    stringBuilder.append(SPACE)
                }
                stringBuilder.append(source[i])
            }
            lastSource = stringBuilder.toString()
            s.replace(0, s.length, lastSource)
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val SPACE = " "
        private var lastSource = EMPTY_STRING
    }
}