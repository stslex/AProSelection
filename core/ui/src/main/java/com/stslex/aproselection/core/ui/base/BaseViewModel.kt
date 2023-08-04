package com.stslex.aproselection.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.stslex.aproselection.core.core.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

open class BaseViewModel : ViewModel() {

    inline fun <T : Any, R : Any> Pager<Int, T>.mapState(
        crossinline transform: suspend (T) -> R
    ): StateFlow<PagingData<R>> = this
        .flow
        .map { pagingData ->
            pagingData.map { item ->
                transform(item)
            }
        }
        .primaryPagingFlow

    fun <T> Flow<T>.stateIn(
        initialValue: T
    ): StateFlow<T> = this.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = initialValue
    )

    val <T : Any> Flow<PagingData<T>>.primaryPagingFlow: StateFlow<PagingData<T>>
        get() = cachedIn(viewModelScope)
            .makeStateFlow(PagingData.empty())

    private fun <T : Any> Flow<T>.makeStateFlow(initialValue: T): StateFlow<T> =
        flowOn(Dispatchers.IO)
            .stateIn(
                initialValue = initialValue
            )

    fun handleError(throwable: Throwable) {
        Logger.exception(throwable)
    }
}