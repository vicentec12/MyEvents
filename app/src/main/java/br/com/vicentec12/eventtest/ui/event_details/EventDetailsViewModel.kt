package br.com.vicentec12.eventtest.ui.event_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vicentec12.eventtest.data.model.Event
import br.com.vicentec12.eventtest.data.source.Result
import br.com.vicentec12.eventtest.data.source.event.EventDataSource
import br.com.vicentec12.eventtest.di.ActivityScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class EventDetailsViewModel @Inject constructor(
    private val mEventRepository: EventDataSource
) : ViewModel() {

    private val _event: MutableLiveData<Event> = MutableLiveData()
    val event: LiveData<Event>
        get() = _event

    private val _errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    private val _viewFlipperChild: MutableLiveData<Int> = MutableLiveData()
    val viewFlipperChild: LiveData<Int>
        get() = _viewFlipperChild

    private val _isToolbarExpanded: MutableLiveData<Boolean> = MutableLiveData()
    val isToolbarExpanded: LiveData<Boolean>
        get() = _isToolbarExpanded

    private val _buttonVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val buttonVisibility: LiveData<Boolean>
        get() = _buttonVisibility

    fun getEventDetails(mEventId: Int) {
        if (event.value == null) {
            viewModelScope.launch {
                _viewFlipperChild.value = CHILD_PROGRESS
                _buttonVisibility.value = false
                when (val result = mEventRepository.getEvent(mEventId)) {
                    is Result.Success -> {
                        _event.value = result.data
                        _viewFlipperChild.value = CHILD_DETAILS
                        _isToolbarExpanded.value = true
                        _buttonVisibility.value = true
                    }
                    is Result.Error -> {
                        _errorMessage.value = result.message
                        _viewFlipperChild.value = CHILD_MESSAGE
                        _isToolbarExpanded.value = false
                    }
                }
            }
        }
    }

    companion object {

        private const val CHILD_PROGRESS = 0
        private const val CHILD_DETAILS = 1
        private const val CHILD_MESSAGE = 2

    }

}