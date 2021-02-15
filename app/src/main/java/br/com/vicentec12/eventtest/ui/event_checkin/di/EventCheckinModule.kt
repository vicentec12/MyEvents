package br.com.vicentec12.eventtest.ui.event_checkin.di

import androidx.lifecycle.ViewModel
import br.com.vicentec12.eventtest.di.FragmentScope
import br.com.vicentec12.eventtest.di.ViewModelKey
import br.com.vicentec12.eventtest.ui.event_checkin.EventCheckinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EventCheckinModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(EventCheckinViewModel::class)
    abstract fun bindEventCheckinViewModel(mEventCheckinViewModel: EventCheckinViewModel): ViewModel

}