package com.nhanbt.demo_kotlin_native.core.response

sealed class BaseResponse<out T> {
    object Loading : BaseResponse<Nothing>()

    data class Success<out T>(
        val data: T
    ) : BaseResponse<T>()

    data class Failure(
        val e: Exception?
    ) : BaseResponse<Nothing>()
}
