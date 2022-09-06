package datn.cnpm.rcsystem.core

data class ErrorMessage(
    val errorCode: String = "",
    val message: String = "",
    val exception: Exception
)