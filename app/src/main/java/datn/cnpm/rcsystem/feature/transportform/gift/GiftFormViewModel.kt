package datn.cnpm.rcsystem.feature.transportform.gift

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.domain.model.TransportFormFirebase
import datn.cnpm.rcsystem.domain.model.history.HistoryStatus
import javax.inject.Inject

@HiltViewModel
class GiftFormViewModel @Inject constructor() :
    BaseViewModel<GiftFormState, GiftFormEvent>() {
    override fun initState() = GiftFormState()

    val database = Firebase.database
    val formList = mutableListOf<TransportFormFirebase>()

    fun listener() {
        SingletonObject.staff?.let {
            database.getReference(TRANSPORT_FORM).child(HistoryStatus.CREATE.name).child(GIFT).child(it.agentId)
                .addChildEventListener(object :
                    ChildEventListener {
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        snapshot.getValue(TransportFormFirebase::class.java)?.let { form ->
                            formList.add(form)
                        }
                        dispatchState(state = currentState.copy(listForm = formList.toList()))
                    }

                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        snapshot.getValue(TransportFormFirebase::class.java)?.let { form ->
                            formList.remove(form)
                        }
                        dispatchState(state = currentState.copy(listForm = formList.toList()))
                    }

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }

    }

    companion object {

        const val TRANSPORT_FORM = "TRANSPORT_FORM"
        const val GIFT = "GIFT"
    }
}