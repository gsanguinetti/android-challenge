package es.npatarino.android.gotchallenge.data.datasource

import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity
import io.reactivex.Completable
import io.reactivex.Single

interface GoTCharactersDataSource {

    fun getAll(): Single<List<GoTCharacterEntity>>

    fun insertAll(characterEntities: List<GoTCharacterEntity>) : List<Long>

    fun deleteAll() :Int
}