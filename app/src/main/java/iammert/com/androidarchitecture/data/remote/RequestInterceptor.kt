package iammert.com.androidarchitecture.data.remote

import iammert.com.androidarchitecture.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build()
        return chain.proceed(request.newBuilder().url(url).build())
    }
}
