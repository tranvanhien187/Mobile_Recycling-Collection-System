package datn.cnpm.rcsystem.feature.form.garbage.complete

import datn.cnpm.rcsystem.domain.model.TransportFormFirebase
import java.io.File

data class CompleteGarbageFormState(
    val loading: Boolean = false,
    val form: TransportFormFirebase? = null,
    val file: File? = null
)