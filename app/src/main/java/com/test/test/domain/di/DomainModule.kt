package com.test.test.domain.di

import androidx.paging.PagedList
import com.test.test.domain.gateway.item.IItemGateway
import com.test.test.domain.gateway.item.ItemGateway
import com.test.test.domain.gateway.item.ItemSourceFactory
import com.test.test.domain.usecase.item.ItemInteractor
import com.test.test.domain.usecase.item.ItemUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object DomainModule {
    fun get() = Kodein.Module("DomainModule") {
        bind<ItemUseCase>() with provider {
            ItemInteractor(
                gateway = instance(),
                config = instance(tag = "itemList"),
                factory = instance()
            )
        }

        bind<IItemGateway>() with provider { ItemGateway(instance()) }

        bind() from provider {
            ItemSourceFactory(
                gateway = instance()
            )
        }

        bind(tag = "itemList") from provider {
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(100)
                .build()
        }


    }
}