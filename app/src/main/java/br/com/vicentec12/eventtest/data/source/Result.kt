package br.com.vicentec12.eventtest.data.source

sealed class Result<out R> {

    data class Success<out T>(val data: T, val message: Int) : Result<T>()
    data class Error(val message: Int) : Result<Nothing>()

}