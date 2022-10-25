package datn.cnpm.rcsystem.feature.form.receive

import datn.cnpm.rcsystem.domain.model.TFormGarbageDetailEntity
import datn.cnpm.rcsystem.domain.model.TransportForm

data class ReceiveFormState(val loading: Boolean = false, val form: TransportForm? = null)