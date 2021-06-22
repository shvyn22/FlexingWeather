package shvyn22.weatherapplication.util

sealed class Resource<T>(
    val data: T? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T> : Resource<T>()
    class Undefined<T> : Resource<T>()
}