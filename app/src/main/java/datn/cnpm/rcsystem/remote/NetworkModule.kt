package datn.cnpm.rcsystem.remote

import android.annotation.SuppressLint
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import datn.cnpm.rcsystem.remote.NetworkConfig.AUTHORIZATION_PREFIX
import datn.cnpm.rcsystem.remote.NetworkConfig.KEY_AUTHORIZATION
import datn.cnpm.rcsystem.remote.NetworkConfig.NETWORK_CONNECT_TIMEOUT
import datn.cnpm.rcsystem.remote.NetworkConfig.NETWORK_READ_TIMEOUT
import datn.cnpm.rcsystem.remote.NetworkConfig.NETWORK_WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {
        @Singleton
        @Provides
        fun provideRetrofit(
            authPreference: AuthPreference,
            requestInterceptor: RequestInterceptor
        ): Retrofit.Builder {

            val okHttpClient = getUnsafeOkHttpClient()
                ?.addInterceptor(requestInterceptor)
                ?.addInterceptor(
                    requestInterceptor(
                        authPreference = authPreference
                    )
                )?.build() ?: return Retrofit.Builder()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConfig.BASE_URL)
                .client(okHttpClient)
        }

        private fun requestInterceptor(authPreference: AuthPreference): Interceptor =
            Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder().method(original.method, original.body)

                // add header common
//                requestBuilder.addHeader(PLATFORM_NAME, PLATFORM_MOBILE)
//                requestBuilder.addHeader(CONTENT_TYPE, VALUE_CONTENT_TYPE)

                // add access token
                authPreference.accessToken.let {
                    DebugLog.d("accessToken=$it")
                    if (it.isNotBlank()) {
                        requestBuilder.addHeader(
                            name = KEY_AUTHORIZATION,
                            value = "$AUTHORIZATION_PREFIX $it"
                        )
                    }
                }

                val request = requestBuilder.build()
                return@Interceptor chain
                    .withConnectTimeout(NETWORK_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .withWriteTimeout(NETWORK_WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .withReadTimeout(NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
                    .proceed(request)
            }

        private fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
            return try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts: Array<TrustManager> = arrayOf(
                    @SuppressLint("CustomX509TrustManager")
                    object : X509TrustManager {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

                // Install the all-trusting trust manager
                val sslContext: SSLContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier { _, _ -> true }
                    .apply {
                        val loggingInterceptor = HttpLoggingInterceptor()
                            .apply { level = HttpLoggingInterceptor.Level.BODY }
                        addInterceptor(loggingInterceptor)
                    }
                builder
            } catch (e: Exception) {
                DebugLog.e(e.message.toString())
                throw RuntimeException(e)
            }
        }
    }

}