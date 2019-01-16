package com.kohls.bootstrap.bootstrapapp.util.ext

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.Transformations
import com.kohls.bootstrap.bootstrapapp.data.api.*
import org.reactivestreams.Publisher

fun <T, X> switchMapForApiResponse(liveData: LiveData<ApiResponse<T>>, doOnSubscribe: (() -> Unit)? = null, doOnSuccess: (((T?) -> X?)?) = null,
                                   doOnError: (((Throwable) -> Unit)?) = null): LiveData<X?>? {

    return Transformations.map(liveData) {
        when (it) {
            is ApiIsLoading -> {
                doOnSubscribe?.invoke()
                null
            }
            is ApiSuccessResponse -> {
                val responseBody = it.body
                doOnSuccess?.invoke(responseBody)
            }
            is ApiEmptyResponse<*> -> {
                doOnSuccess?.invoke(null)
                null
            }
            is ApiErrorResponse<*> -> {
                doOnError?.invoke(it.errorMessage)
                null
            }
            else -> null
        }
    }
}

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this)

fun <T> LiveData<T>.toPublisher(lifecycleOwner: LifecycleOwner)
    = LiveDataReactiveStreams.toPublisher(lifecycleOwner, this)
