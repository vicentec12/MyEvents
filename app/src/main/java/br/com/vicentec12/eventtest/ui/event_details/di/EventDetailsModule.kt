package br.com.vicentec12.eventtest.ui.event_details.di

import androidx.lifecycle.ViewModel
import br.com.vicentec12.eventtest.di.ActivityScope
import br.com.vicentec12.eventtest.di.ViewModelKey
import br.com.vicentec12.eventtest.ui.event_details.EventDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EventDetailsModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(EventDetailsViewModel::class)
    abstract fun bindEventDetailsViewModel(mEventDetailsViewModel: EventDetailsViewModel): ViewModel

}