package datn.cnpm.rcsystem.feature.transportform.list

import datn.cnpm.rcsystem.domain.model.TransportForm

data class TransportFormState(val loading: Boolean = false, val listForm: List<TransportForm> = emptyList())