package net.taraabar.network.states

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ApiCallState {
    private val _showLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _hasError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow("")
    private val _exception: MutableStateFlow<Throwable?> = MutableStateFlow(null)

    internal fun setState(
        loading: Boolean,
        error: Boolean = false,
        message: String? = null,
        exception: Throwable? = null
    ) {
        _showLoading.value = loading
        _hasError.value = error
        _errorMessage.value = message
        _exception.value = exception
    }

    val showLoading = _showLoading.asStateFlow()
    val hasError = _hasError.asStateFlow()
    val errorMessage = _errorMessage.asStateFlow()
    val exception = _exception.asStateFlow()

    fun clearStates() = setState(loading = false, error = false, message = null, exception = null)
}

val loadingApiCallState = ApiCallState().apply { setState(loading = true) }
fun errorApiCallState(message: String) =
    ApiCallState().apply { setState(loading = false, error = true, message = message) }

