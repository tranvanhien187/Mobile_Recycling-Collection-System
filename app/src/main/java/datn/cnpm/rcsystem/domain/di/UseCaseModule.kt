package datn.cnpm.rcsystem.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import datn.cnpm.rcsystem.domain.usecase.*
import datn.cnpm.rcsystem.domain.usecase.gift.GetGiftDetailUseCase
import datn.cnpm.rcsystem.domain.usecase.gift.GetGiftDetailUseCaseImpl
import datn.cnpm.rcsystem.domain.usecase.gift.GetGiftOwnerByAgentUseCase
import datn.cnpm.rcsystem.domain.usecase.gift.GetGiftOwnerByAgentUseCaseImpl
import datn.cnpm.rcsystem.domain.usecase.statistic.GetStatisticStaffCollection7DayUseCase
import datn.cnpm.rcsystem.domain.usecase.statistic.GetStatisticStaffCollection7DayUseCaseImpl
import datn.cnpm.rcsystem.domain.usecase.statistic.GetStatisticTopStaffCollectUseCase
import datn.cnpm.rcsystem.domain.usecase.statistic.GetStatisticTopStaffCollectUseCaseImpl
import datn.cnpm.rcsystem.domain.usecase.transport.*
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
        useCase: GetCustomerInfoUseCaseImpl
    ): GetCustomerInfoUseCase

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

    @Binds
    @Singleton
    abstract fun provideGetTPlaceDetailUseCase(
        useCase: GetTPlaceDetailUseCaseImpl
    ): GetTPlaceDetailUseCase

    @Binds
    @Singleton
    abstract fun provideGetGiftDetailUseCase(
        useCase: GetGiftDetailUseCaseImpl
    ): GetGiftDetailUseCase

    @Binds
    @Singleton
    abstract fun provideGetGiftByCriteriaUseCase(
        useCase: GetGiftByCriteriaUseCaseImpl
    ): GetGiftByCriteriaUseCase

    @Binds
    @Singleton
    abstract fun provideCreateTransportGarbageFormUseCase(
        useCase: CreateTransportGarbageFormUseCaseImpl
    ): CreateTransportGarbageFormUseCase

    @Binds
    @Singleton
    abstract fun provideCompleteTransportFormUseCase(
        useCase: CompleteTransportFormUseCaseImpl
    ): CompleteTransportFormUseCase

    @Binds
    @Singleton
    abstract fun provideGetGiftOwnerByAgent(
        useCase: GetGiftOwnerByAgentUseCaseImpl
    ): GetGiftOwnerByAgentUseCase

    @Binds
    @Singleton
    abstract fun provideGetStatisticStaffCollection7DayUseCase(
        useCase: GetStatisticStaffCollection7DayUseCaseImpl
    ): GetStatisticStaffCollection7DayUseCase
    
    @Binds
    @Singleton
    abstract fun provideGetStatisticTopStaffCollectUseCase(
        useCase: GetStatisticTopStaffCollectUseCaseImpl
    ): GetStatisticTopStaffCollectUseCase
}