package datn.cnpm.rcsystem.data.authentication.di

import datn.cnpm.rcsystem.data.authentication.datastore.AuthenticationApiService
import datn.cnpm.rcsystem.data.authentication.datastore.AuthenticationDataSource
import datn.cnpm.rcsystem.data.authentication.repository.AuthenticationRepository
import datn.cnpm.rcsystem.data.authentication.repository.AuthenticationRepositoryImp
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
    abstract fun provideAuthenticationRepository(authenticationRepository: AuthenticationRepositoryImp): AuthenticationRepository

    companion object {

        @Provides
        @Singleton
        fun provideAuthenticationDataSource(apiService: AuthenticationApiService): AuthenticationDataSource {
            return AuthenticationDataSource(apiService)
        }

        @Provides
        @Singleton
        fun provideAuthApiService(retrofitBuilder: Retrofit.Builder): AuthenticationApiService {
            return retrofitBuilder
                .build()
                .create(AuthenticationApiService::class.java)
        }
    }
}