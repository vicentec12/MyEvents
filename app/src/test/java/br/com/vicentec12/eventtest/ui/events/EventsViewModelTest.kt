package br.com.vicentec12.eventtest.ui.events

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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventsViewModelTest {

    @get:Rule
    val mTestInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mTestCoroutineRule = TestCoroutineRule()

    lateinit var mViewModel: EventsViewModel

    @Mock
    private lateinit var mEventRepository: EventRepository

    @Mock
    private lateinit var mEventsObserver: Observer<List<Event>>

    @Mock
    private lateinit var mViewFlipperObserver: Observer<Int>

    @Mock
    private lateinit var mErrorMessageObserver: Observer<Int>

    @Before
    fun setup() {
        mViewModel = EventsViewModel(mEventRepository)
        mViewModel.events.observeForever(mEventsObserver)
        mViewModel.viewFlipperChild.observeForever(mViewFlipperObserver)
        mViewModel.errorMessage.observeForever(mErrorMessageObserver)
    }

    @Test
    fun `when success given list events and change view flipper child to recycler`() {
        mTestCoroutineRule.runBlockingTest {
            // Arrange
            `when`(mEventRepository.listEvents())
                .thenReturn(Result.Success(emptyList(), 1))

            // Act
            mViewModel.listEvents()

            // Assert
            verify(mViewFlipperObserver).onChanged(0)
            verify(mEventsObserver).onChanged(emptyList())
            verify(mViewFlipperObserver).onChanged(1)
        }
    }

    @Test
    fun `when error given error message and change view flipper child to message`() {
        mTestCoroutineRule.runBlockingTest {
            // Arrange
            `when`(mEventRepository.listEvents()).thenReturn(Result.Error(2))

            // Act
            mViewModel.listEvents()

            // Assert
            verify(mViewFlipperObserver).onChanged(0)
            verify(mErrorMessageObserver).onChanged(2)
            verify(mViewFlipperObserver).onChanged(2)
        }
    }

    @After
    fun after() {
        mViewModel.events.removeObserver(mEventsObserver)
        mViewModel.viewFlipperChild.removeObserver(mViewFlipperObserver)
    }

}