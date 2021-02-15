package br.com.vicentec12.eventtest.ui.event_checkin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.vicentec12.eventtest.data.source.Result
import br.com.vicentec12.eventtest.data.source.event.EventRepository
import br.com.vicentec12.eventtest.util.TestCoroutineRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventCheckinViewModelTest {

    @get:Rule
    val mTestInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mTestCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var mRepository: EventRepository

    @Mock
    lateinit var mResultCodeObserver: Observer<Int>

    @Mock
    lateinit var mViewFlipperObserver: Observer<Int>

    private lateinit var mViewModel: EventCheckinViewModel

    @Before
    fun setup() {
        mViewModel = EventCheckinViewModel(mRepository)
        mViewModel.resultCode.observeForever(mResultCodeObserver)
        mViewModel.viewFlipperChild.observeForever(mViewFlipperObserver)
    }

    @Test
    fun `when success given code and set view flipper`() {
        mTestCoroutineRule.runBlockingTest {
            // Arrange
            Mockito.`when`(mRepository.checkinEvent(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Result.Success(200, 1));

            // Act
            mViewModel.effectCheckin(Mockito.anyInt())

            // Assert
            Mockito.verify(mViewFlipperObserver).onChanged(0)
            Mockito.verify(mResultCodeObserver).onChanged(200)
            Mockito.verify(mViewFlipperObserver).onChanged(1)
        }
    }

    @Test
    fun `when error give error message and set view flipper child to message`() {
        mTestCoroutineRule.runBlockingTest {
            // Arrange
            Mockito.`when`(mRepository.getEvent(Mockito.anyInt())).thenReturn(Result.Error(1))

            // Act
            mViewModel.effectCheckin(Mockito.anyInt())

            // Assert
            Mockito.verify(mViewFlipperObserver).onChanged(1)
        }
    }

    @After
    fun after() {
        mViewModel.resultCode.removeObserver(mResultCodeObserver)
        mViewModel.viewFlipperChild.removeObserver(mViewFlipperObserver)
    }

}