package rajesh.com.mvpnews.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import rajesh.com.mvpnews.BuildConfig
import rajesh.com.mvpnews.util.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Created by rajeshkantipudi on 01/11/18
 */

/**
 * ApiInterface to provide a apiClient to make network calls and define api end points
 */
interface ApiInterface {

    companion object Factory {
        fun create(): ApiInterface {

            val clientBuilder = OkHttpClient.Builder()
            val headerAuthorizationInterceptor = Interceptor { chain ->
                var request = chain.request()
                val headers = request.headers().newBuilder().add("Authorization", BuildConfig.API_KEY).build()
                request = request.newBuilder().headers(headers).build()
                chain.proceed(request)
            }
            clientBuilder.addInterceptor(headerAuthorizationInterceptor)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(clientBuilder.build())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

    @GET("v2/top-headlines")
    fun getTopNews(@Query("country") country: String): Call<TopHeadlinesResponse>
}