package datn.cnpm.rcsystem.data.di

import datn.cnpm.rcsystem.data.datastore.CRGSApiService
import datn.cnpm.rcsystem.data.datastore.CRGSDataSource
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.data.repository.CRGSRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {

    @Binds
    @Singleton
    abstract fun provideAuthenticationRepository(authenticationRepository: CRGSRepositoryImp): CRGSRepository

    companion object {

        @Provides
        @Singleton
        fun provideAuthenticationDataSource(apiService: CRGSApiService): CRGSDataSource {
            return CRGSDataSource(apiService)
        }

        @Provides
        @Singleton
        fun provideAuthApiService(retrofitBuilder: Retrofit.Builder): CRGSApiService {
            return retrofitBuilder
                .build()
                .create(CRGSApiService::class.java)
        }
    }
}