package com.clevmania.binlookup.utils

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher


/**
 * @author by Lawrence on 12/22/20.
 * for BinLookup
 */

class CardFormatter : TextWatcher {
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {
        if (s.isNotEmpty() && s.length % 5 == 0) {
            val c = s[s.length - 1]
            if (space == c) {
                s.delete(s.length - 1, s.length)
            }
        }

        if (s.isNotEmpty() && s.length % 5 == 0) {
            val c = s[s.length - 1]
            if (Character.isDigit(c) && TextUtils.split(s.toString(), space.toString()).size <= 3) {
                s.insert(s.length - 1, space.toString())
            }
        }
    }

    companion object {
        private const val space = ' '
    }
}