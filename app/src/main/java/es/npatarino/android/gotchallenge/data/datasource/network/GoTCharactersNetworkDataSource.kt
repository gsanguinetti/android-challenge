package es.npatarino.android.gotchallenge.data.datasource.network

import es.npatarino.android.gotchallenge.data.datasource.GoTCharactersDataSource
import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity
import io.reactivex.Completable
import io.reactivex.Single

class GoTCharactersNetworkDataSource(
        private val networkService: NetworkService) : GoTCharactersDataSource {

    private val characters = mutableListOf<GoTCharacterEntity>()

    override fun getAll(): Single<List<GoTCharacterEntity>> =
            if (characters.isNotEmpty()) Single.just(characters)
            else networkService.makeApiCallForResponse(GoTCharacterRemoteApi::class.java) {
                it.getGoTCharacters()
            }.map {
                characters.clear()
                characters.addAll(it)
                it
            }

    override fun insertAll(characterEntities: List<GoTCharacterEntity>): List<Long> {
        throw NotImplementedError()
    }

    override fun deleteAll(): Int {
        throw NotImplementedError()
    }
}