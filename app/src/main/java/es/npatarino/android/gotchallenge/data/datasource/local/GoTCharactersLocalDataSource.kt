package es.npatarino.android.gotchallenge.data.datasource.local

import es.npatarino.android.gotchallenge.data.datasource.GoTCharactersDataSource
import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity
import io.reactivex.Completable
import io.reactivex.Single

class GoTCharactersLocalDataSource(
        private val charactersDatabase: GoTCharacterDatabase
) : GoTCharactersDataSource {

    override fun getAll(): Single<List<GoTCharacterEntity>> =
            charactersDatabase.characterDao().getAll()

    override fun insertAll(characterEntities: List<GoTCharacterEntity>): List<Long> =
            charactersDatabase.characterDao().insertAll(*characterEntities.toTypedArray())

    override fun deleteAll(): Int =
            charactersDatabase.characterDao().deleteAll()
}