package com.coveiot.android.footballleagues.network

interface SportsApiListener<T, E> {
    fun onSuccess(obj: T)

    fun onError(obj: E)
}