package br.com.vicentec12.eventtest.ui.event_checkin.di

import br.com.vicentec12.eventtest.di.FragmentScope
import br.com.vicentec12.eventtest.ui.event_checkin.EventCheckinFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [EventCheckinModule::class])
interface EventCheckinComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): EventCheckinComponent

    }

    fun inject(mFragment: EventCheckinFragment)

}