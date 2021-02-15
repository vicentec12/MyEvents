package br.com.vicentec12.eventtest.di

import android.content.Context
import br.com.vicentec12.eventtest.data.source.event.EventDataSourceModule
import br.com.vicentec12.eventtest.data.source.retrofit_api.RetrofitModule
import br.com.vicentec12.eventtest.ui.event_checkin.di.EventCheckinComponent
import br.com.vicentec12.eventtest.ui.event_details.di.EventDetailsComponent
import br.com.vicentec12.eventtest.ui.events.di.EventsComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RetrofitModule::class,
        EventDataSourceModule::class,
        ViewModelProviderFactoryModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Context): AppComponent

    }

    fun eventsComponent(): EventsComponent.Factory

    fun eventDetailsComponent(): EventDetailsComponent.Factory

    fun eventCheckinComponent(): EventCheckinComponent.Factory

}