package datn.cnpm.rcsystem.feature.transportform.gift

import datn.cnpm.rcsystem.domain.model.TransportFormFirebase

data class GiftFormState(val loading: Boolean = false, val listForm: List<TransportFormFirebase> = emptyList())