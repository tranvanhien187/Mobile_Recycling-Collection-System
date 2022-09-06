package datn.cnpm.rcsystem.local.sharepreferences

import android.content.SharedPreferences
import javax.inject.Inject

interface AuthPreference {

    var isFirstLogin: Boolean

    var accessToken: String

    var lastLoggedInUsername: String

    var lastLoggedInFullName: String

    var lastLoggedInCompanyName: String

    var lastPhoneNumber: String

    var lastAuthMethod: String

    var lastLoggedInAvatar: String

    val hasSession: Boolean

    var deviceId: String

    suspend fun deleteAllPreference()

    var lastLoginErrorCode: String

    suspend fun deleteLoginErrorPreference()

    var userId: String
}

class AuthPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AuthPreference {
    companion object {
        private const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
        private const val FIRST_LOGIN = "FIRST_LOGIN"

        private const val LAST_LOGGED_IN_USERNAME_KEY = "LAST_LOGGED_IN_USERNAME_KEY"
        private const val LAST_LOGGED_IN_FULL_NAME_KEY = "LAST_LOGGED_IN_FULL_NAME_KEY"
        private const val LAST_LOGGED_IN_COMPANY_NAME_KEY = "LAST_LOGGED_IN_COMPANY_NAME_KEY"
        private const val LAST_LOGGED_IN_AVATAR_KEY = "LAST_LOGGED_IN_AVATAR_KEY"
        private const val LAST_LOGGED_PHONE_NUMBER = "LAST_LOGGED_PHONE_NUMBER"
        private const val LAST_LOGGED_AUTH_METHOD = "LAST_LOGGED_AUTH_METHOD"
        private const val USER_ID_KEY = "USER_ID_KEY"
        private const val DEVICE_ID = "DEVICE_ID"
        private const val LAST_LOGGED_IN_ERROR_KEY = "LAST_LOGGED_IN_ERROR_KEY"
    }

    override var isFirstLogin: Boolean by BooleanPreferenceDelegate(sharedPreferences, FIRST_LOGIN)

    override var accessToken: String by StringPreferenceDelegate(sharedPreferences, ACCESS_TOKEN_KEY)

    override var userId: String by StringPreferenceDelegate(sharedPreferences, USER_ID_KEY)

    override var lastLoggedInUsername: String by StringPreferenceDelegate(
        sharedPreferences,
        LAST_LOGGED_IN_USERNAME_KEY
    )

    override var lastLoggedInFullName: String by StringPreferenceDelegate(
        sharedPreferences,
        LAST_LOGGED_IN_FULL_NAME_KEY
    )

    override var lastLoggedInCompanyName: String by StringPreferenceDelegate(
        sharedPreferences,
        LAST_LOGGED_IN_COMPANY_NAME_KEY
    )
    override var lastPhoneNumber: String by StringPreferenceDelegate(
        sharedPreferences,
        LAST_LOGGED_PHONE_NUMBER
    )
    override var lastAuthMethod: String by StringPreferenceDelegate(
        sharedPreferences, LAST_LOGGED_AUTH_METHOD
    )

    override var lastLoggedInAvatar: String by StringPreferenceDelegate(
        sharedPreferences,
        LAST_LOGGED_IN_AVATAR_KEY
    )

    override var lastLoginErrorCode: String by StringPreferenceDelegate(
        sharedPreferences,
        LAST_LOGGED_IN_ERROR_KEY
    )

    override val hasSession: Boolean by KeyExistPreferenceDelegate(sharedPreferences, ACCESS_TOKEN_KEY)


    override var deviceId: String by StringPreferenceDelegate(
        sharedPreferences,
        DEVICE_ID
    )

    override suspend fun deleteAllPreference() {
        sharedPreferences.removeData(LAST_LOGGED_IN_USERNAME_KEY)
        sharedPreferences.removeData(LAST_LOGGED_IN_FULL_NAME_KEY)
        sharedPreferences.removeData(LAST_LOGGED_IN_COMPANY_NAME_KEY)
        sharedPreferences.removeData(LAST_LOGGED_IN_AVATAR_KEY)
    }

    override suspend fun deleteLoginErrorPreference() {
        sharedPreferences.removeData(LAST_LOGGED_IN_ERROR_KEY)
    }
}
