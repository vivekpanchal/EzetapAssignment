package com.vivek.ezetapassignment.utils.customViews

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.vivek.ezetapassignment.R
import com.vivek.networklib.response.Uidata

class UIType(context: Context, data: Uidata) {
    private lateinit var textView: TextView
    private lateinit var editText: EditText
    private lateinit var button: Button
    init {
        when (data.uitype) {
            "label" -> {
                textView = TextView(context)
                textView.hint = data.hint
                textView.tag=data.key
                textView.text= data.value
                textView.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                textView.setPadding(20, 5, 20, 5)
            }
            "edittext" -> {
                editText = EditText(context)
                when (data.key) {
                    "text_name" -> {
                        editText.id = R.id.text_name
                        editText.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                    }
                    "text_phone" -> {
                        editText.id = R.id.text_phone
                        editText.inputType = InputType.TYPE_CLASS_PHONE
                        editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))
                    }
                    "text_city" -> {
                        editText.id = R.id.text_city
                        editText.inputType = InputType.TYPE_CLASS_TEXT
                    }
                    else -> editText.id = R.id.unknown
                }
                editText.hint = data.hint
                editText.tag = data.key
                editText.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    setMargins(0,0,0,20)
                }
                editText.setPadding(20, 20, 20, 20)
                editText.setText(data.value)

            }
            "button" -> {
                button = Button(context)
                button.tag = data.value
                button.id= R.id.submit_button
                button.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    setMargins(0,60,0,0)
                }
                button.setPadding(0, 5, 0, 5)
                button.text = data.value
                button.setOnClickListener(context as View.OnClickListener)
            }
        }
    }

    fun getView(): View? {
        return when {
            ::textView.isInitialized -> textView
            ::editText.isInitialized -> editText
            ::button.isInitialized -> button
            else -> null
        }
    }
}
