package es.npatarino.android.gotchallenge.data

import android.accounts.NetworkErrorException
import es.npatarino.android.gotchallenge.data.datasource.GoTCharactersDataSource
import es.npatarino.android.gotchallenge.data.datasource.local.GoTCharactersLocalDataSource
import es.npatarino.android.gotchallenge.data.datasource.network.GoTCharactersNetworkDataSource
import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity
import io.reactivex.Single
import java.lang.Exception

class GoTDataProvider(
        private val localGoTCharactersDataSource: GoTCharactersLocalDataSource,
        private val remoteGoTCharactersDataSource: GoTCharactersNetworkDataSource) {

    fun getGoTCharacters() :Single<List<GoTCharacterEntity>> =
        remoteGoTCharactersDataSource.getAll()
                .onErrorResumeNext {
                    localGoTCharactersDataSource.getAll()
                            .flatMap {
                                if(it.isEmpty()) Single.error(NetworkErrorException())
                                else Single.just(it)
                            }
                }
                .doOnSuccess {
                    localGoTCharactersDataSource.deleteAll()
                    localGoTCharactersDataSource.insertAll(it)
                }
}