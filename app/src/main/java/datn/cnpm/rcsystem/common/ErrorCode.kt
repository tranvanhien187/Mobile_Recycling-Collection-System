package datn.cnpm.rcsystem.common

enum class ErrorCode(val value: String) {
    SHORT_USERNAME("Username must have at least 6 letters"),
    EXISTED_USERNAME("This username already existed!"),
    INVALID_EMAIL("Email is not valid"),
    SHORT_PASSWORD("Password must have at least 6 letters"),
    RETYPE_PASSWORD("Please make sure your password match"),
    EXISTED_EMAIL("This email already existed!"),
    UNKNOWN_ERROR("We don't find this error");


}