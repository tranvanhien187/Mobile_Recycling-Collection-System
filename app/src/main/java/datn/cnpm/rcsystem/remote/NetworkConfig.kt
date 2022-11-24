package datn.cnpm.rcsystem.remote

object NetworkConfig {

    // Header key
    const val KEY_AUTHORIZATION = "Authorization"

    const val AUTHORIZATION_PREFIX = "Bearer"

    // network timeout
    const val NETWORK_CONNECT_TIMEOUT = 90

    const val NETWORK_WRITE_TIMEOUT = 90

    const val NETWORK_READ_TIMEOUT = 90

    const val BASE_URL = "http://crgs-env.eba-ju3banit.ap-northeast-1.elasticbeanstalk.com/"
//    const val BASE_URL = "https://c0ad-2402-800-629c-6379-f962-e217-54ea-38fa.ap.ngrok.io/"
}