package br.com.vicentec12.eventtest.ui.event_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.vicentec12.eventtest.data.model.Event
import br.com.vicentec12.eventtest.data.source.Result
import br.com.vicentec12.eventtest.data.source.event.EventRepository
import br.com.vicentec12.eventtest.util.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventDetailsViewModelTest {

    @get:Rule
    val mTestInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mTestCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var mRepository: EventRepository

    @Mock
    lateinit var mEventObserver: Observer<Event>

    @Mock
    lateinit var mViewFlipperObserver: Observer<Int>

    @Mock
    lateinit var mErrorMessageObserver: Observer<Int>

    @Mock
    lateinit var mIsToolbarExpandedObserver: Observer<Boolean>

    private lateinit var mViewModel: EventDetailsViewModel

    @Before
    fun setup() {
        mViewModel = EventDetailsViewModel(mRepository)
        mViewModel.event.observeForever(mEventObserver)
        mViewModel.viewFlipperChild.observeForever(mViewFlipperObserver)
        mViewModel.errorMessage.observeForever(mErrorMessageObserver)
        mViewModel.isToolbarExpanded.observeForever(mIsToolbarExpandedObserver)
    }

    @Test
    fun `when success given event and set view flipper child to event details`() {
        mTestCoroutineRule.runBlockingTest {
            // Arrange
            val mEvent = Event(1, "", "", "", 0.0, 0.0, 0.0, 0)
            `when`(mRepository.getEvent(anyInt())).thenReturn(Result.Success(mEvent, 1));

            // Act
            mViewModel.getEventDetails(anyInt())

            // Assert
            verify(mViewFlipperObserver).onChanged(0)
            verify(mIsToolbarExpandedObserver).onChanged(false)
            verify(mEventObserver).onChanged(mEvent)
            verify(mViewFlipperObserver).onChanged(1)
            verify(mIsToolbarExpandedObserver).onChanged(true)
        }
    }

    @Test
    fun `when error give error message and set view flipper child to message`() {
        mTestCoroutineRule.runBlockingTest {
            // Arrange
            `when`(mRepository.getEvent(anyInt())).thenReturn(Result.Error(1))

            // Act
            mViewModel.getEventDetails(anyInt())

            // Assert
            verify(mViewFlipperObserver).onChanged(0)
            verify(mErrorMessageObserver).onChanged(1)
            verify(mViewFlipperObserver).onChanged(2)
            verify(mIsToolbarExpandedObserver).onChanged(false)
        }
    }

    @After
    fun after() {
        mViewModel.event.removeObserver(mEventObserver)
        mViewModel.viewFlipperChild.removeObserver(mViewFlipperObserver)
        mViewModel.errorMessage.removeObserver(mErrorMessageObserver)
        mViewModel.isToolbarExpanded.removeObserver(mIsToolbarExpandedObserver)
    }

}