package datn.cnpm.rcsystem.local.sharepreferences

import android.content.Context
import android.content.SharedPreferences
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class PreferenceStorageModule {

    @Binds
    @Singleton
    abstract fun provideAuthPreference(authPreferenceImpl: AuthPreferenceImpl): AuthPreference

    companion object {
        @Provides
        @Singleton
        fun providerPreference(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences(
                "PREFERENCE_FILE",
                Context.MODE_PRIVATE
            )
        }
    }
}