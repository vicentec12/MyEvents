package br.com.vicentec12.eventtest.ui.events

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
class EventsViewModel @Inject constructor(
    private val mEventsRepository: EventDataSource
) : ViewModel() {

    private val _events: MutableLiveData<List<Event>> = MutableLiveData()
    val events: LiveData<List<Event>>
        get() = _events

    private val _errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    private val _viewFlipperChild: MutableLiveData<Int> = MutableLiveData()
    val viewFlipperChild: LiveData<Int>
        get() = _viewFlipperChild

    fun listEvents() {
        viewModelScope.launch {
            _viewFlipperChild.value = CHILD_PROGRESS
            when (val result = mEventsRepository.listEvents()) {
                is Result.Success -> {
                    _events.value = result.data
                    _viewFlipperChild.value = CHILD_RECYCLER
                }
                is Result.Error -> {
                    _errorMessage.value = result.message
                    _viewFlipperChild.value = CHILD_MESSAGE
                }
            }
        }
    }

    companion object {

        private const val CHILD_PROGRESS = 0
        private const val CHILD_RECYCLER = 1
        private const val CHILD_MESSAGE = 2

    }

}