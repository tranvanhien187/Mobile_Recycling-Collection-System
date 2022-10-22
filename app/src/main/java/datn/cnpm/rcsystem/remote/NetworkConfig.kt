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
}