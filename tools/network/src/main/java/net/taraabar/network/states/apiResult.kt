package net.taraabar.network.states


import net.taraabar.network.base.CustomResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
suspend fun <R, T : CustomResult<R>> Flow<T>.apiResultCollector(
    state: ApiCallState,
    showSnakeBarOnError: Boolean = false,
    useFullScreenLoading: Boolean = false,
    success: suspend (R) -> Unit
) {
    collect {
        when (it) {
            is CustomResult.Loading -> {
                state.setState(true)
                if (useFullScreenLoading) {
                    TODO("show full screen loading")
                }
            }

            is CustomResult.Error -> {
                state.setState(
                    loading = false,
                    error = true,
                    message = it.exception.message,
                    exception = it.exception
                )
                if (useFullScreenLoading) {
                    TODO("gone full screen loading")
                }
                CoroutineScope(Dispatchers.Main).launch {
                    if (showSnakeBarOnError) {
                        TODO("show snackbar for CONNECTION_PROBLEM")

                    }
                }
            }

            is CustomResult.Empty -> {
                state.setState(false)
                if (useFullScreenLoading) {
                    TODO("gone full screen loading")
                }
            }

            is CustomResult.Success<*> -> {
                state.setState(loading = false)
                if (useFullScreenLoading) {
                    TODO("gone full screen loading")
                }
                success(it.data as R)

            }
        }
    }
}