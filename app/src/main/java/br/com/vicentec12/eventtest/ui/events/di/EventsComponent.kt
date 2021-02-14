package br.com.vicentec12.eventtest.ui.events.di

import br.com.vicentec12.eventtest.di.ActivityScope
import br.com.vicentec12.eventtest.ui.events.EventsActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [EventsModule::class])
interface EventsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): EventsComponent

    }

    fun inject(mActivity: EventsActivity)

}