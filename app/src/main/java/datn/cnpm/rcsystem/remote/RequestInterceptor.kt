package datn.cnpm.rcsystem.remote

import datn.cnpm.rcsystem.core.logging.DebugLog
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class RequestInterceptor @Inject constructor() :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        // 1. sign this request
        val request: Request = chain.request()

        // 2. proceed with the request
        val response: Response = chain.proceed(request)

        DebugLog.d("response code = ${response.code}")
        // check if still unauthorized (i.e. refresh failed)
        if (response.code == 401) {
            DebugLog.d("token unauthorized")
        }
        // returning the response to the original request
        return if (response.code < 200 || response.code >= 300) {
            val body = response.body
            response.newBuilder().code(200)
                .body(body)
                .build()
        } else {
            response
        }
    }
}