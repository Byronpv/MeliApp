package com.bypv.meliapp.core

/**
 * A sealed class that represents a data resource that can be in one of three states:
 * Loading, Success, or Failure.
 *
 * @param T the type of data that the resource holds in case of success.
 */
sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val exception: Exception) : Resource<Nothing>()
}