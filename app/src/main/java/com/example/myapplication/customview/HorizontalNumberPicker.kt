package com.example.myapplication.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.core.widget.doOnTextChanged
import com.example.myapplication.R
import timber.log.Timber


class HorizontalNumberPicker(context: Context?, @Nullable attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val et_number: EditText?
    var min = 1
    var max = 100

    /***
     * HANDLERS
     */
    private inner class AddHandler(val diff: Int) : OnClickListener {
        override fun onClick(v: View?) {
            var newValue = value + diff
            if (newValue < min) {
                newValue = min
            } else if (newValue > max) {
                newValue = max
            }
            et_number!!.setText(newValue.toString())
        }
    }

    /***
     * GETTERS & SETTERS
     */
    var value: Int
        get() {
            if (et_number != null) {
                try {
                    val value = et_number.text.toString()
                    return value.toInt()
                } catch (ex: NumberFormatException) {
                    Timber.e("HorizontalNumberPicker", ex.toString())
                }
            }
            return 0
        }
        set(value) {
            et_number?.setText(value.toString())
        }

    init {
        inflate(context, R.layout.quantity_custom_view, this)
        et_number = findViewById(R.id.et_number)
        val btn_less: Button = findViewById(R.id.btn_less)
        btn_less.setOnClickListener(AddHandler(-1))
        val btn_more: Button = findViewById(R.id.btn_more)
        btn_more.setOnClickListener(AddHandler(1))
        et_number.doOnTextChanged { text, _, _, _ ->
            run {
                if (text.toString().toInt() > max) {
                    et_number!!.setText(max.toString())
                }
            }
        }
    }
}