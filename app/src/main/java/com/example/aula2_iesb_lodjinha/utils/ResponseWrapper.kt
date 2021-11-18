package com.example.aula2_iesb_lodjinha.utils

sealed class ResponseWrapper<out T> {
    data class Success<T>(val result: T) : ResponseWrapper<T>()
    data class Error(val errorResponse: String? = null) : ResponseWrapper<Nothing>()
}
