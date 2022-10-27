package datn.cnpm.rcsystem.feature.dashboard.staff

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.base.BaseViewModel
import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.core.failed
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.core.requireData
import datn.cnpm.rcsystem.core.requireError
import datn.cnpm.rcsystem.core.succeeded
import datn.cnpm.rcsystem.domain.model.TransportForm
import datn.cnpm.rcsystem.domain.model.history.HistoryStatus
import datn.cnpm.rcsystem.domain.usecase.GetStaffInfoUseCase
import datn.cnpm.rcsystem.feature.transportform.list.TransportFormViewModel
import kotlinx.coroutines.launch
import java.security.SignedObject
import javax.inject.Inject

@HiltViewModel
class DashboardStaffViewModel @Inject constructor(
    private val getStaffInfoUseCase: GetStaffInfoUseCase
) :
    BaseViewModel<DashboardStaffState, DashboardStaffEvent>() {
    override fun initState() = DashboardStaffState()


    fun fetchStaffInfo() {
        viewModelScope.launch {
            dispatchState(currentState.copy(loading = true))
            val response =
                getStaffInfoUseCase.getStaffInfo()
            if (response.succeeded) {
                listener()
                dispatchState(state = currentState.copy(staff = response.requireData))
            } else if (response.failed) {
                when (response.requireError.errorCode) {
                    ErrorCode.NOT_FIND_STAFF_ID -> {
                        DebugLog.e(ErrorCode.NOT_FIND_STAFF_ID.value)
                    }
                    else -> {
                        DebugLog.e(ErrorCode.UNKNOWN_ERROR.value)
                    }
                }
            } else {
                DebugLog.e(response.requireError.message)
            }
            dispatchState(currentState.copy(loading = false))
        }
    }


    val database = Firebase.database
    val formList = mutableListOf<TransportForm>()

    fun listener() {
        database.getReference(TransportFormViewModel.TRANSPORT_FORM).child(TransportFormViewModel.GARBAGE).child(
            HistoryStatus.RECEIVE.name).child(SingletonObject.staff!!.id).addChildEventListener(object :
            ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.getValue(TransportForm::class.java)?.let {
                    formList.add(it)
                    Log.d("AAAAA", it.id)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                snapshot.getValue(TransportForm::class.java)?.let {
                    formList.remove(it)
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}