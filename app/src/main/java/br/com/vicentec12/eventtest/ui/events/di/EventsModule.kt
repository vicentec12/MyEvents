package br.com.vicentec12.eventtest.ui.events.di

import androidx.lifecycle.ViewModel
import br.com.vicentec12.eventtest.di.ActivityScope
import br.com.vicentec12.eventtest.di.ViewModelKey
import br.com.vicentec12.eventtest.ui.events.EventsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EventsModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    abstract fun bindEventeViewModel(mEventsViewModel: EventsViewModel): ViewModel

}