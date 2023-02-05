package com.vivek.ezetapassignment.di

import com.vivek.ezetapassignment.utils.customViews.LoadingView
import com.vivek.ezetapassignment.utils.customViews.NoDataAvailable
import com.vivek.networklib.NetworkImpl
import com.vivek.networklib.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =NetworkImpl()

    @Singleton
    @Provides
    fun provideLoadingView(): LoadingView = LoadingView()


    @Singleton
    @Provides
    fun provideNoDataAvailable(): NoDataAvailable = NoDataAvailable()

}