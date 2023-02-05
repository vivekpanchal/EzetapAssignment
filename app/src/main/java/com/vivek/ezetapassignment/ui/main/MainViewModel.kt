package com.vivek.ezetapassignment.ui.main

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivek.ezetapassignment.data.Repository
import com.vivek.ezetapassignment.utils.baseclasses.BaseViewModel
import com.vivek.ezetapassignment.utils.network.NetworkHelper
import com.vivek.ezetapassignment.utils.onException
import com.vivek.ezetapassignment.utils.onSuccess
import com.vivek.networklib.NetworkService
import com.vivek.networklib.Resource
import com.vivek.networklib.response.UiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repo: Repository,
    networkHelper: NetworkHelper
) : BaseViewModel(networkHelper){

    init {
        fetchUiDta()
    }

    private val _uiData = MutableLiveData<Resource<UiResponse>>(Resource.Loading())
    val uiData: LiveData<Resource<UiResponse>>
        get() = _uiData


    private val _imgBitmap = MutableLiveData<Resource<Bitmap>>()
    val imgBitmap: LiveData<Resource<Bitmap>>
        get() = _imgBitmap



    fun fetchUiDta() = viewModelScope.launch {
        Timber.d("fetching data.................")
        if (checkInternetConnectionWithMessage()){
            Timber.d("fetching data")
            repo.fetchUi()
                .onSuccess { res->
                    Timber.d("data fetched-> $res")
                    fetchBitmap(res.logoUrl)
                    _uiData.postValue(Resource.Success(res))

                }.onException { err->
                    Timber.e("error-> ${err.localizedMessage?.toString()}")
                    _uiData.postValue(Resource.Error(err.localizedMessage?.toString()))
                    handleNetworkError(err)
                }
        }

    }


    fun fetchBitmap(url:String) = viewModelScope.launch {
        Timber.d("fetching bitmap.................")
        if (checkInternetConnectionWithMessage()){
            repo.fetchLogo(url)
                .onSuccess { res->
                    Timber.d("data fetched-> $res")
                    _imgBitmap.postValue(Resource.Success(res))
                }.onException { err->
                    Timber.e("error-> ${err.localizedMessage?.toString()}")
                    handleNetworkError(err)
                    _imgBitmap.postValue(Resource.Error(err.localizedMessage?.toString()))
                }
        }

    }
}