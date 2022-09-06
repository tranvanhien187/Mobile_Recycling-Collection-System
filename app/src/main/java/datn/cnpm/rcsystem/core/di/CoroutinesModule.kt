package datn.cnpm.rcsystem.core.di

import com.example.basesource.common.loader.LogoLoaderDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import datn.cnpm.rcsystem.core.di.DefaultDispatcher
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.core.di.MainDispatcher
import datn.cnpm.rcsystem.core.di.MainImmediateDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesModule {

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

    @Provides
    fun proVideAdapter(): LogoLoaderDialog = LogoLoaderDialog()
}
