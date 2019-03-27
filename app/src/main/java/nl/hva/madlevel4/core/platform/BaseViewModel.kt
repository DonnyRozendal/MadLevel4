package nl.hva.madlevel4.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.hva.madlevel4.core.exception.Failure

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure?) {
        this.failure.value = failure
    }

}