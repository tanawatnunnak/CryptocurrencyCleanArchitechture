package com.tanawatnunnak.cryptocurrencycleanarchitechture.common

import retrofit2.Response


fun<T, R> Response<T>.toResource(mapper: (t:T) ->R): Resource<R>{
    val body = this.body()
    return if (isSuccessful && body != null) {
        Resource.Success(data = mapper(body))
    } else {
        Resource.Error(message = this.message())
    }
}