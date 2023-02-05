package com.vivek.ezetapassignment.utils.customViews

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.vivek.ezetapassignment.R
import javax.inject.Singleton

@Singleton
class LoadingView {

    private lateinit var dialog: Dialog

    fun createView(context: Context) {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_loading_layout)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.window!!.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )
    }

    fun show() {
        if (!dialog.isShowing)
            dialog.show()

    }

    fun hide() {
        if (dialog.isShowing)
            dialog.dismiss()
    }


}