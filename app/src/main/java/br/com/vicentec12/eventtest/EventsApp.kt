package br.com.vicentec12.eventtest

import android.app.Application
import br.com.vicentec12.eventtest.di.AppComponent
import br.com.vicentec12.eventtest.di.DaggerAppComponent

class EventsApp : Application() {

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.factory().create(this)
    }

}