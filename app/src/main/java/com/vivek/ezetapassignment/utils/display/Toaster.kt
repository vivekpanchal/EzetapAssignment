package com.vivek.ezetapassignment.utils.display

import android.content.Context
import timber.log.Timber

object Toaster {
    fun show(context: Context, text: CharSequence) {
        Timber.d("text-> $text")
        val toast = android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT)
//        toast.view?.background?.setColorFilter(
//            ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN
//        )
//        val textView = toast.view?.findViewById(android.R.id.message) as TextView
//        textView.setTextColor(ContextCompat.getColor(context, R.color.black))
        toast.show()
    }


    fun showLong(context: Context, text: CharSequence) {
        Timber.d("text-> $text")
        val toast = android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_LONG)
//        toast.view?.background?.setColorFilter(
//            ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN
//        )
//        val textView = toast.view?.findViewById(android.R.id.message) as TextView
//        textView.setTextColor(ContextCompat.getColor(context, R.color.black))
        toast.show()
    }
}