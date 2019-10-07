package es.npatarino.android.gotchallenge.data.datasource.network

import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity
import io.reactivex.Single
import retrofit2.http.GET

interface GoTCharacterRemoteApi {

    @GET("characters.json")
    fun getGoTCharacters() : Single<List<GoTCharacterEntity>>
}