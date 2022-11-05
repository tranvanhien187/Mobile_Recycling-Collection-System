package datn.cnpm.rcsystem.feature.transportform.garbage

import datn.cnpm.rcsystem.domain.model.TransportFormFirebase


data class GarbageFormState (val loading: Boolean = false, val listForm: List<TransportFormFirebase> = emptyList())