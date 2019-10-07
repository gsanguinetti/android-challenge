package es.npatarino.android.gotchallenge.characters.repository

import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter
import es.npatarino.android.gotchallenge.characters.domain.repository.GoTCharacterListRepository
import es.npatarino.android.gotchallenge.data.GoTDataProvider
import io.reactivex.Single

class GoTCharacterListDataRepository(
        private val dataProvider: GoTDataProvider,
        private val characterDataMapper: GoTCharacterDataMapper
) : GoTCharacterListRepository {

    override fun getCharacterList(): Single<List<GoTCharacter>> =
            dataProvider.getGoTCharacters()
                    .map { list -> list.map { characterDataMapper.mapFromEntity(it) } }
}