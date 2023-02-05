package com.vivek.ezetapassignment.di

import com.vivek.ezetapassignment.data.Repository
import com.vivek.ezetapassignment.data.RepositoryIml
import com.vivek.networklib.NetworkImpl
import com.vivek.networklib.NetworkService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by Vivek Panchal on 04/02/23
 * https://vivekpanchal.dev
 * Copyright (c) 04/02/23 . All rights reserved.
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindRepository(impl: RepositoryIml): Repository

}