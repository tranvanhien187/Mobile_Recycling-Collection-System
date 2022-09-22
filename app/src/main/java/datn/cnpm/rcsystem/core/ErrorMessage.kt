package datn.cnpm.rcsystem.core

import datn.cnpm.rcsystem.common.ErrorCode

data class ErrorMessage(
    val errorCode: ErrorCode = ErrorCode.UNKNOWN_ERROR,
    val message: String = "",
    val exception: Exception
)