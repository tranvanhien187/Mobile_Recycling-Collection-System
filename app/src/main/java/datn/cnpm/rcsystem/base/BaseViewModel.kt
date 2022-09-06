package datn.cnpm.rcsystem.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.basesource.common.utils.reactive.SingleLiveEvent

abstract class BaseViewModel<S : Any, E> : ViewModel(), StoreObservable<S> {
    private val store by lazy {
        ViewStateStore(this.initState())
    }

    val currentState: S
        get() = store.state

    private val _liveEvent: SingleLiveEvent<E> = SingleLiveEvent()
    val liveEvent: LiveData<E>
        get() = _liveEvent

    abstract fun initState(): S

    override fun <T> observe(owner: LifecycleOwner, selector: (S) -> T, observer: Observer<T>) {
        store.observe(owner, selector, observer)
    }

    protected fun dispatchEvent(event: E) {
        _liveEvent.value = event ?: return
    }

    protected fun dispatchState(state: S) {
        store.dispatchState(state = state)
    }
}