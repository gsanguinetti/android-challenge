package es.npatarino.android.gotchallenge.data.datasource.network

import es.npatarino.android.gotchallenge.data.datasource.network.model.ServerAddress
import io.reactivex.Maybe
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService(
        private val serverAddress: ServerAddress) {

    private fun getApiBuilder(): Retrofit =
            Retrofit.Builder()
                    .baseUrl(serverAddress.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build()

    fun <DATA : Any, T> makeApiCallForResponse(
            apiClass: Class<T>,
            apiCall: ((api: T) -> Single<DATA>)
    ): Single<DATA> =
            apiCall(getApiBuilder().create(apiClass))
}