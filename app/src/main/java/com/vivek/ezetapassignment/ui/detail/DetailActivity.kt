package com.vivek.ezetapassignment.ui.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import androidx.activity.viewModels
import com.vivek.ezetapassignment.R
import com.vivek.ezetapassignment.databinding.DetailActivityBinding
import com.vivek.ezetapassignment.utils.baseclasses.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailActivity :
    BaseActivity<DetailActivityBinding, DetailViewModel>(R.layout.activity_detail) {


    override val viewModel: DetailViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapValues :HashMap<String,String>? =intent.getSerializableExtra("map") as HashMap<String,String>
        val image: Bitmap? = intent.getParcelableExtra("BitmapImage") as Bitmap?
        image?.let { binding.imgLogo.setImageBitmap(it) }

        mapValues?.forEach {
            Timber.d("Key ${it.key} Value ${it.value}")
            when(it.key){
                "text_name" -> {
                    binding.tvName.text = "Name: ${it.value}"
                }
                "text_phone" -> {
                    binding.tvPhone.text = "Phone: ${it.value}"
                }
                "text_city" -> {
                    binding.tvCity.text = "City: ${it.value}"
                }
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()

    }

}