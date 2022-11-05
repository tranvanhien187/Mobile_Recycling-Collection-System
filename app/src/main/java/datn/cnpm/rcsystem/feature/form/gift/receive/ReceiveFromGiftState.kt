package datn.cnpm.rcsystem.feature.form.gift.receive

import datn.cnpm.rcsystem.domain.model.TransportFormFirebase

data class ReceiveFromGiftState(val loading: Boolean = false, val form: TransportFormFirebase? = null)