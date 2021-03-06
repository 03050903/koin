package org.koin.androidx.viewmodel.ext.android

import androidx.lifecycle.ViewModel
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ViewModelOwnerDefinition
import org.koin.androidx.viewmodel.scope.BundleDefinition
import org.koin.androidx.viewmodel.scope.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import kotlin.reflect.KClass

/**
 * ComponentActivity extensions to help for ViewModel
 *
 * @author Arnaud Giuliani
 */
inline fun <reified T : ViewModel> ScopeActivity.viewModel(
    qualifier: Qualifier? = null,
    noinline state: BundleDefinition? = null,
    noinline owner: ViewModelOwnerDefinition = { ViewModelOwner.from(this, this) },
    noinline parameters: ParametersDefinition? = null
): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        getViewModel<T>(qualifier, state, owner, parameters)
    }
}

fun <T : ViewModel> ScopeActivity.viewModel(
    qualifier: Qualifier? = null,
    state: BundleDefinition? = null,
    owner: ViewModelOwnerDefinition = { ViewModelOwner.from(this, this) },
    clazz: KClass<T>,
    parameters: ParametersDefinition? = null
): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) { getViewModel(qualifier, state, owner, clazz, parameters) }
}

inline fun <reified T : ViewModel> ScopeActivity.getViewModel(
    qualifier: Qualifier? = null,
    noinline state: BundleDefinition? = null,
    noinline owner: ViewModelOwnerDefinition = { ViewModelOwner.from(this, this) },
    noinline parameters: ParametersDefinition? = null
): T {
    return getViewModel(qualifier, state, owner, T::class, parameters)
}

fun <T : ViewModel> ScopeActivity.getViewModel(
    qualifier: Qualifier? = null,
    state: BundleDefinition? = null,
    owner: ViewModelOwnerDefinition = { ViewModelOwner.from(this, this) },
    clazz: KClass<T>,
    parameters: ParametersDefinition? = null
): T {
    return scope.getViewModel(qualifier, state, owner, clazz, parameters)
}