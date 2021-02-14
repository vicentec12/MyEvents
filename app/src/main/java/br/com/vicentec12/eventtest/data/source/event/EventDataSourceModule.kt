package br.com.vicentec12.eventtest.data.source.event

import br.com.vicentec12.eventtest.data.source.Remote
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class EventDataSourceModule {

    @Singleton
    @Remote
    @Binds
    abstract fun bindEventRemoteDataSource(mEventRemoteDataSource: EventRemoteDataSource): EventDataSource

    @Singleton
    @Binds
    abstract fun bindEventRepository(mEventRepository: EventRepository): EventDataSource

}