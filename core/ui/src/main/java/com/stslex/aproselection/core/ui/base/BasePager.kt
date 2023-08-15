package com.stslex.aproselection.core.ui.base

import androidx.paging.Pager
import androidx.paging.PagingConfig

object BasePager {

    const val DEFAULT_PAGING_PAGE_SIZE = 15

    @JvmStatic
    val DEFAULT_PAGING_CONFIG = PagingConfig(
        pageSize = DEFAULT_PAGING_PAGE_SIZE,
        enablePlaceholders = false
    )

    fun <ItemType : Any> makePager(
        getItems: suspend (page: Int, pageSize: Int) -> List<ItemType>,
        config: PagingConfig = DEFAULT_PAGING_CONFIG
    ): Pager<Int, ItemType> = Pager(config) {
        BasePagingSource(getItems)
    }
}



