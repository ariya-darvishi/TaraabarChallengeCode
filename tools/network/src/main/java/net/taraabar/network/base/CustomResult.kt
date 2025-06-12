package net.taraabar.network.base

sealed class CustomResult<out T> {

    data class Success<out T>(
        val data: T
    ) : CustomResult<T>() {
        operator fun invoke(): T {
            return data
        }
    }

    data class Error(val exception: Throwable) : CustomResult<Nothing>() {
        constructor(error: Error) : this(error.exception)
    }


    object Loading : CustomResult<Nothing>() {
        override fun hashCode(): Int {
            return 2
        }

        override fun equals(other: Any?): Boolean {
            return other is Loading
        }
    }

    object Empty : CustomResult<Nothing>() {
        override fun hashCode(): Int {
            return 3
        }

        override fun equals(other: Any?): Boolean {
            return other is Empty
        }
    }
}