package ca.qc.cstj.s09navigationdrawer.core

import com.google.android.material.textfield.TextInputLayout

var TextInputLayout.text
    get() = editText!!.text.toString()
    set(value) = editText!!.setText(value)