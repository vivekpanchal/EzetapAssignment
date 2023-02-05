package com.vivek.ezetapassignment.utils.baseclasses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vivek.ezetapassignment.R
import com.vivek.ezetapassignment.utils.Event
import com.vivek.ezetapassignment.utils.network.NetworkHelper
import com.vivek.networklib.Resource
import javax.net.ssl.HttpsURLConnection


/**
 * Created by Vivek Panchal
 * https://vivekpanchal.dev
  All rights reserved.
 */

abstract class BaseViewModel(
    protected val networkHelper: NetworkHelper,
) : ViewModel() {


    val messageStringId: MutableLiveData<Event<Resource<Int>>> = MutableLiveData()
    val messageString: MutableLiveData<Event<Resource<String>>> = MutableLiveData()


    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Event(Resource.Error(null, R.string.network_connection_error)))
            false
        }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    protected fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status) {
                    -1 -> messageStringId.postValue(Event(Resource.Error(null,
                        R.string.network_default_error)))
                    0 -> messageStringId.postValue(Event(Resource.Error(null,
                        R.string.server_connection_error)))
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        messageStringId.postValue(Event(Resource.Error(null,
                            R.string.network_login_again)))
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(Event(Resource.Error(null,
                            R.string.network_internal_error)))
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(Event(Resource.Error(
                            null, R.string.network_server_not_available)))
                    else -> messageString.postValue(Event(Resource.Error(message)))
                }
            }
        }


    fun displayError(error: String) {
        messageString.postValue(Event(Resource.Error(null,error)))
    }

}