package datn.cnpm.rcsystem.domain.di

import datn.cnpm.rcsystem.domain.usecase.LoginUseCase
import datn.cnpm.rcsystem.domain.usecase.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun provideLoginUseCase(
        useCase: LoginUseCaseImpl,
    ): LoginUseCase
}