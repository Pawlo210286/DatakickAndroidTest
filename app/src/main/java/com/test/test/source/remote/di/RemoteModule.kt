package com.test.test.source.remote.di

import com.test.test.source.remote.api.ItemApi
import com.test.test.source.remote.repository.item.IItemRepository
import com.test.test.source.remote.repository.item.ItemRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

object RemoteModule {
    fun get() = Kodein.Module("RemoteModule") {
        import(RetrofitModule.get())

        bind<ItemApi>() with singleton { instance<Retrofit>().create(ItemApi::class.java) }

        bind<IItemRepository>() with provider { ItemRepository(instance()) }
    }
}