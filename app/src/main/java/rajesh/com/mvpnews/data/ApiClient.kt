package rajesh.com.mvpnews.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import rajesh.com.mvpnews.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by rajeshkantipudi on 01/11/18
 */

class ApiClient {

    companion object {
        val BASE_URL: String = "https://newsapi.org/"
        private var retrofit: Retrofit? = null
    }

    fun getApiClient(): Retrofit = retrofit ?: synchronized(this) {
        retrofit ?: buildClient().also { retrofit = it }
    }

    private fun buildClient(): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        val headerAuthorizationInterceptor = Interceptor { chain ->
            var request = chain.request()
            val headers = request.headers().newBuilder().add("Authorization", BuildConfig.API_KEY).build()
            request = request.newBuilder().headers(headers).build()
            chain.proceed(request)
        }
        clientBuilder.addInterceptor(headerAuthorizationInterceptor)
        return Retrofit.Builder().client(clientBuilder.build()).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


}