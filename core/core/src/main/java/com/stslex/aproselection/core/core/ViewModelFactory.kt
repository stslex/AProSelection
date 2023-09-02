package com.stslex.aproselection.core.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var viewModel = viewModelMap[modelClass]
        if (viewModel == null) {
            for (entry in viewModelMap) {
                if (modelClass.isAssignableFrom(entry.key)) {
                    viewModel = entry.value
                    break
                }
            }
        }
        if (viewModel == null) throw IllegalArgumentException("Unknown model class $modelClass")
        @Suppress("UNCHECKED_CAST")
        return viewModel.get() as T
    }

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(
//        modelClass: Class<T>
//    ): T = viewModelMap
//        .getOrElse(modelClass) {
//            viewModelMap.firstNotNullOfOrNull { entry ->
//                entry.takeIf { modelClass.isAssignableFrom(it.key) }
//            }
//        } as? T
//        ?: throw IllegalArgumentException("Unknown model class $modelClass")
}