package com.example.nasaexplorer.logic

/**
 * A generic class that holds a value or an exception
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>() {
        override fun toString(): String {
            return "Success[data=$data]"
        }
    }
    data class Error(val exception: Exception) : Result<Nothing>() {
        override fun toString(): String {
            return "Error[exception=$exception]"
        }
    }
}

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}