package datn.cnpm.rcsystem.feature.transportform.list

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.domain.model.GarbageHistoryStatus
import datn.cnpm.rcsystem.domain.model.GiftStatus
import datn.cnpm.rcsystem.domain.model.TransportForm
import datn.cnpm.rcsystem.feature.history.HistoryState
import javax.inject.Inject

@HiltViewModel
class TransportFormViewModel @Inject constructor() :
    BaseViewModel<TransportFormState, TransportFormEvent>() {
    override fun initState() = TransportFormState()

    val database = Firebase.database
    val formList = mutableListOf<TransportForm>()

    fun listener() {
        database.getReference(TRANSPORT_FORM).child(GARBAGE).child(GarbageHistoryStatus.CREATE.name).addChildEventListener(object :
            ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.getValue(TransportForm::class.java)?.let {
                    formList.add(it)
                }
                dispatchState(state = currentState.copy(listForm = formList.toList()))
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                snapshot.getValue(TransportForm::class.java)?.let {
                    formList.remove(it)
                }
                dispatchState(state = currentState.copy(listForm = formList.toList()))
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    companion object {

        const val TRANSPORT_FORM = "TRANSPORT_FORM"
        const val GIFT = "GIFT"
        const val GARBAGE = "GARBAGE"
        const val AVAILABLE = "AVAILABLE"
    }
}