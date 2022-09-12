package datn.cnpm.rcsystem.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import datn.cnpm.rcsystem.domain.usecase.*
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
        useCase: LoginUseCaseImpl
    ): LoginUseCase

    @Binds
    @Singleton
    abstract fun provideResetPasswordUseCase(
        useCase: ChangePasswordUseCaseImpl
    ): ChangePasswordUseCase

    @Binds
    @Singleton
    abstract fun provideRegisterUseCase(
        useCase: RegisterUseCaseImpl
    ): RegisterUseCase

    @Binds
    @Singleton
    abstract fun provideGenOTPUseCase(
        useCase: GenOTPUseCaseImpl
    ): GenOTPUseCase

    @Binds
    @Singleton
    abstract fun provideValidateOTPUseCase(
        useCase: ValidateOTPUseCaseImpl
    ): ValidateOTPUseCase

}