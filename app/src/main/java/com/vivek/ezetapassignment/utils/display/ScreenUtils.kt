package com.vivek.ezetapassignment.utils.display

import android.app.Activity
import android.content.res.Resources
import android.view.View

object ScreenUtils {

    fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

    fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels




    fun changeStatusBarTextColor(activity: Activity) {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

}