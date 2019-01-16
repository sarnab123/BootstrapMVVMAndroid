package com.kohls.bootstrap.bootstrapapp.data.di


class HeaderInterceptor :  okhttp3.Interceptor {
    override fun intercept(chain: okhttp3.Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("x-channel", "android")
            .addHeader("gcp-platform", "true")
            .addHeader("x-correlation-id","abc544")
            .build()
        return chain.proceed(request)
    }

}

