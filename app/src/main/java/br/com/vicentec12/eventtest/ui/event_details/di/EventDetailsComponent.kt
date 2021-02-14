package br.com.vicentec12.eventtest.ui.event_details.di

import br.com.vicentec12.eventtest.di.ActivityScope
import br.com.vicentec12.eventtest.ui.event_details.EventDetailsActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [EventDetailsModule::class])
interface EventDetailsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): EventDetailsComponent

    }

    fun inject(mActivity: EventDetailsActivity)

}