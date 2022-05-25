package com.coveiot.android.footballleagues.network

import android.content.Context
import com.coveiot.android.footballleague.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap

class SportsApiClient {
    companion object {
        private val TAG = SportsApiClient()!!::class.java.simpleName
        private var sportsApiClient: SportsApiClient? = null
        private var mContext: Context? = null

        @JvmStatic
        @Synchronized
        fun initSportsApi(context: Context?) {
            if (sportsApiClient == null) {
                mContext = context
                sportsApiClient = SportsApiClient()
            }
        }
        fun getService(): SportsApiClient? {
            if (sportsApiClient == null) {
                sportsApiClient = SportsApiClient()
            }
            return sportsApiClient
        }
        fun getSportsHeaders(): HashMap<String, String> {
            val customHeaders = HashMap<String, String>()
            return customHeaders
        }
    }

    fun getSportsClientService(): SportsApiService {
        return getSportsClient()!!.create(SportsApiService::class.java)

    }


    private fun getSportsClient(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        var retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.NEW_BUILD_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }

}