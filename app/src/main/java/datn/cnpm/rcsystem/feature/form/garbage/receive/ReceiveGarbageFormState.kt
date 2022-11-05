package datn.cnpm.rcsystem.feature.form.garbage.receive

import datn.cnpm.rcsystem.domain.model.TransportFormFirebase

data class ReceiveGarbageFormState(val loading: Boolean = false, val form: TransportFormFirebase? = null)