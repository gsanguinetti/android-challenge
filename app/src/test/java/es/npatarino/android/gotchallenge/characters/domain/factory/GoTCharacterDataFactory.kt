package es.npatarino.android.gotchallenge.characters.domain.factory

import es.npatarino.android.gotchallenge.base.test.randomString
import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter

class GoTCharacterDataFactory {
    companion object {
        fun makeGoTCharacter(houseId: String = randomString(), name: String = randomString()) =
                GoTCharacter(
                        name,
                        randomString(),
                        houseId,
                        randomString()
                )
    }
}