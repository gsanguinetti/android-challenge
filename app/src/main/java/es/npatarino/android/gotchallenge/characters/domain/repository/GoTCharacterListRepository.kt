package es.npatarino.android.gotchallenge.characters.domain.repository

import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter
import io.reactivex.Single

interface GoTCharacterListRepository {
    fun getCharacterList(): Single<List<GoTCharacter>>
}