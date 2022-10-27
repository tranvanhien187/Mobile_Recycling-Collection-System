package datn.cnpm.rcsystem.feature.form.complete

import datn.cnpm.rcsystem.domain.model.TransportForm
import java.io.File

data class CompleteFormState(
    val loading: Boolean = false,
    val form: TransportForm? = null,
    val file: File? = null
)