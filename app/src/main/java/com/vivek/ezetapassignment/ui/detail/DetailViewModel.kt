package com.vivek.ezetapassignment.ui.detail

import com.vivek.ezetapassignment.utils.baseclasses.BaseViewModel
import com.vivek.ezetapassignment.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(networkHelper: NetworkHelper) :
    BaseViewModel(networkHelper) {
}