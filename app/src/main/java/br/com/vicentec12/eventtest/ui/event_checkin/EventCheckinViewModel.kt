package br.com.vicentec12.eventtest.ui.event_checkin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vicentec12.eventtest.data.source.Result
import br.com.vicentec12.eventtest.data.source.event.EventDataSource
import br.com.vicentec12.eventtest.di.FragmentScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScope
class EventCheckinViewModel @Inject constructor(
    private val mRepository: EventDataSource
) : ViewModel() {

    val _name: MutableLiveData<String> = MutableLiveData()

    val _email: MutableLiveData<String> = MutableLiveData()

    private val _message: MutableLiveData<Int> = MutableLiveData()
    val message: LiveData<Int>
        get() = _message

    private val _resultCode: MutableLiveData<Int> = MutableLiveData()
    val resultCode: LiveData<Int>
        get() = _resultCode

    private val _viewFlipperChild: MutableLiveData<Int> = MutableLiveData()
    val viewFlipperChild: LiveData<Int>
        get() = _viewFlipperChild

    fun effectCheckin(mEventId: Int) {
        viewModelScope.launch {
            _viewFlipperChild.value = CHILD_PROGRESS
            when (val mResult =
                mRepository.checkinEvent(mEventId, _name.value ?: "", _email.value ?: "")) {
                is Result.Success -> {
                    _message.value = mResult.message
                    _resultCode.value = mResult.data
                }
                is Result.Error -> {
                    _message.value = mResult.message
                    _resultCode.value = 101
                }
            }
            _viewFlipperChild.value = CHILD_CHECKIN
        }
    }

    companion object {

        private const val CHILD_CHECKIN = 0
        private const val CHILD_PROGRESS = 1

    }

}