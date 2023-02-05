package com.vivek.ezetapassignment.ui.main

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import com.vivek.ezetapassignment.R
import com.vivek.ezetapassignment.databinding.MainActivityBinding
import com.vivek.ezetapassignment.ui.detail.DetailActivity
import com.vivek.ezetapassignment.utils.baseclasses.BaseActivity
import com.vivek.ezetapassignment.utils.customViews.UIType
import com.vivek.networklib.Resource
import com.vivek.networklib.response.UiResponse
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding,MainViewModel>(R.layout.activity_main), View.OnClickListener {

    override val viewModel:MainViewModel by viewModels()

    var img:Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun setupObservers() {
        super.setupObservers()
        lifecycleScope.launchWhenCreated {
            viewModel.uiData.observe(this@MainActivity) {
                when (it) {
                    is Resource.Error -> {
                        hideLoading()
                        showMessage(it.message.toString())
                    }
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                       Timber.d("Success ${it.data}")
                        it.data?.let {response->
                            noDataView.hide()
                            handleUiData(response)
                        }?: kotlin.run {
                            noDataView.show()
                        }
                    }
                }
            }

            viewModel.imgBitmap.observe(this@MainActivity) {
                when (it) {
                    is Resource.Error -> {
                      //show default bitmap
                        binding.imgLogo.setImageDrawable(getDrawable(R.drawable.ic_launcher_background))
                    }
                    is Resource.Loading -> {
                        //show default bitmap
                    }
                    is Resource.Success -> {
                        it.data?.let {response->
                            img=response
                            binding.imgLogo.setImageBitmap(response)
                        }?: kotlin.run {
                            binding.imgLogo.setImageDrawable(getDrawable(R.drawable.ic_launcher_background))
                        }
                    }
                }
            }
        }

    }

    private fun handleUiData(data: UiResponse) {
        binding.tvHeading.text = data.headingText
        data.uidata.forEach {
            val type=UIType(this,it)
            Timber.d("Type ${it.uitype}")
            type.getView()?.let {view->
                binding.llForm.addView(view)
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.tag){
            "Submit"->{
                val data=HashMap<String,String>()
                binding.llForm.children.forEach {element->
                  if (element is EditText){
                      data[element.tag.toString()]=element.text.toString()
                  }
                }
                Timber.d("Data $data")
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("map", data)
                intent.putExtra("BitmapImage", img);
                startActivity(intent)
            }
        }
    }
}