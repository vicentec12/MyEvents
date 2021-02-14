package br.com.vicentec12.eventtest.di

import br.com.vicentec12.eventtest.ui.event_details.di.EventDetailsComponent
import br.com.vicentec12.eventtest.ui.events.di.EventsComponent
import dagger.Module

@Module(
    subcomponents = [
        EventsComponent::class,
        EventDetailsComponent::class
    ]
)
abstract class AppModule