package com.vivek.ezetapassignment.utils.baseclasses

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.vivek.ezetapassignment.utils.EventObserver
import com.vivek.ezetapassignment.utils.customViews.LoadingView
import com.vivek.ezetapassignment.utils.customViews.NoDataAvailable
import com.vivek.ezetapassignment.utils.display.Toaster
import javax.inject.Inject


/**
 * Created by Vivek Panchal on 16/06/21
 * https://vivekpanchal.dev
 * Copyright (c) 16/06/21 . All rights reserved.
 */

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val layoutRes: Int) :
    AppCompatActivity() {

    protected abstract val viewModel: VM

    protected lateinit var binding: VB


    @Inject
    lateinit var loadingView: LoadingView

    @Inject
    lateinit var noDataView: NoDataAvailable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        loadingView.createView(this)
        noDataView.createNoDataView(binding.root)
        setupObservers()

    }


    protected open fun setupObservers() {
        viewModel.messageString.observe(this, EventObserver {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, EventObserver {
            it.data?.run { showMessage(this) }
        })
    }


    fun showLoading(msg: String = "Loading...") {
        if (::loadingView.isInitialized) {
            loadingView.show()
        }
    }

    fun hideLoading() {
        if (::loadingView.isInitialized) {
            loadingView.hide()
        }


    }

    fun showMessage(message: String) = baseContext?.let { Toaster.show(it, message) }
    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }


}
