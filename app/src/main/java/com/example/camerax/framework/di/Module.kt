package com.example.camerax.framework.di

import com.example.camerax.core.data.CameraRepository
import com.example.camerax.core.data.CameraRepositoryImpl
import com.example.camerax.core.domain.TakePhotoUseCase
import com.example.camerax.core.domain.TakePhotoUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface Module {

    @Binds
    fun bindsCameraRepository(
        repository: CameraRepositoryImpl
    ): CameraRepository

    @Binds
    fun bindsTakePhotoUseCase(
        takePhotoUseCaseImp: TakePhotoUseCaseImp
    ): TakePhotoUseCase
}