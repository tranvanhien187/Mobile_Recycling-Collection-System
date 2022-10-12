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

    @Binds
    @Singleton
    abstract fun provideUpdateUserInfoUseCase(
        useCase: UpdateUserInfoUseCaseImpl
    ): UpdateUserInfoUseCase

    @Binds
    @Singleton
    abstract fun provideGetUserInfoUseCase(
        useCase: GetUserInfoUseCaseImpl
    ): GetUserInfoUseCase

    @Binds
    @Singleton
    abstract fun provideGetTPlaceForUserUseCase(
        useCase: GetTPlaceForUserUseCaseImpl
    ): GetTPlaceForUserUseCase

    @Binds
    @Singleton
    abstract fun provideGetTPlaceRandom6UseCase(
        useCase: GetTPlaceRandom6UseCaseImpl
    ): GetTPlaceRandom6UseCase

    @Binds
    @Singleton
    abstract fun provideGetGiftUserHistoryUseCase(
        useCase: GetGiftUserHistoryUseCaseImpl
    ): GetGiftUserHistoryUseCase

    @Binds
    @Singleton
    abstract fun provideGetGarbageUserHistoryUseCase(
        useCase: GetGarbageUserHistoryUseCaseImpl
    ): GetGarbageUserHistoryUseCase

    @Binds
    @Singleton
    abstract fun provideGetGiftRandom6UserCase(
        useCase: GetGiftRandom6UserCaseImpl
    ): GetGiftRandom6UserCase


    @Binds
    @Singleton
    abstract fun provideReceiveTransportFormUseCase(
        useCase: ReceiveTransportFormUseCaseImpl
    ): ReceiveTransportFormUseCase

    @Binds
    @Singleton
    abstract fun provideGetStaffInfoUseCase(
        useCase: GetStaffInfoUseCaseImpl
    ): GetStaffInfoUseCase

    @Binds
    @Singleton
    abstract fun provideGetListGarbageHistoryUseCase(
        useCase: GetListGarbageHistoryUseCaseImpl
    ): GetListGarbageHistoryUseCase

    @Binds
    @Singleton
    abstract fun provideGetListGiftHistoryUseCase(
        useCase: GetListGiftHistoryUseCaseImpl
    ): GetListGiftHistoryUseCase

    @Binds
    @Singleton
    abstract fun provideGetGiftHistoryDetailUseCase(
        useCase: GetGiftHistoryDetailUseCaseImpl
    ): GetGiftHistoryDetailUseCase
    
    @Binds
    @Singleton
    abstract fun provideGetGarbageHistoryDetailUseCase(
        useCase: GetGarbageHistoryDetailUseCaseImpl
    ): GetGarbageHistoryDetailUseCase
}