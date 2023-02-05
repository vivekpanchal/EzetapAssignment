package com.vivek.ezetapassignment.utils.customViews

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.vivek.ezetapassignment.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoDataAvailable @Inject constructor() {
    private lateinit var view: View
    private var tvMsg: TextView? = null

    fun createNoDataView(root: View) {
        val layout = root.rootView as ViewGroup
        (LayoutInflater.from(layout.context)
            .inflate(R.layout.custom_no_data_available, layout, false) as View)
            .also {
                view = it
            }

        tvMsg = view.findViewById(R.id.tv_msg)

        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )

        val rl = RelativeLayout(root.context)
            .apply {
                gravity = Gravity.CENTER
                addView(view)
            }

        layout.addView(rl, params)
        hide()
    }

    fun show(msg: String = "") {
        if (::view.isInitialized) {
            if (!view.isVisible) {
                view.isVisible = true
                if (msg.isNotEmpty() && msg != view.context.resources.getString(R.string.no_data)) {
                    tvMsg?.text = msg
                    tvMsg?.isVisible = true
                } else {
                    tvMsg?.isVisible = false
                }
            }
        }
    }

    fun changeMessage(msg: String) {
        if (::view.isInitialized) {
            tvMsg?.let {
                if (msg.isNotEmpty() && msg != view.context.resources.getString(R.string.no_data)) {
                    it.text = msg
                } else {
                    it.text = ""
                }
            }
        }
    }

    fun hide() {
        if (::view.isInitialized) {
            if (view.isVisible) {
                view.isVisible = false
            }
        }
    }
}