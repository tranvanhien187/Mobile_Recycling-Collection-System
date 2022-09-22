package datn.cnpm.rcsystem.common

enum class ErrorCode(val value: String) {
    SHORT_USERNAME("Username must have at least 6 letters"),
    EXISTED_USERNAME("This username already existed!"),
    INVALID_EMAIL("Email is not valid"),
    SHORT_PASSWORD("Password must have at least 6 letters"),
    RETYPE_PASSWORD("Please make sure your password match"),
    NOT_UPDATE_DEALER("You must go to headquarter to update information before become dealer!"),
    EXISTED_EMAIL("This email already existed!"),
    EMPTY_NAME("Name cannot be blank!"),
    EMPTY_IDC("Identify card number cannot be blank!"),
    EMPTY_STREET("Street cannot be blank!"),
    EMPTY_DISTRICT("District cannot be blank!"),
    EMPTY_PROVINCE_CITY("Province/City cannot be blank!"),
    CAN_NOT_UPDATE_WITH_ID_USER("Can not update information with this id!"),
    UNKNOWN_ERROR("We don't find this error");


}