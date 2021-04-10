package com.fkhan.simplenews.repository

sealed class Result<out T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Error(val exception: Throwable) : Result<Nothing>() {

        val isNetworkError: Boolean get() = exception is Exception
    }

    object Empty : Result<Nothing>()

    object Loading : Result<Nothing>()

    companion object {

        fun <T> success(data: T) = Success(data)

        fun error(exception: Throwable) = Error(exception)

        fun empty() = Empty

        fun loading() = Loading

        fun <T> successOrEmpty(list: List<T>): Result<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }
    }
}
