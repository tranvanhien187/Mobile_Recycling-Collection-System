package datn.cnpm.rcsystem.feature.form.gift.complete

import datn.cnpm.rcsystem.domain.model.TransportFormFirebase
import java.io.File

data class CompleteGiftFormState(
    val loading: Boolean = false,
    val form: TransportFormFirebase? = null,
    val file: File? = null
)