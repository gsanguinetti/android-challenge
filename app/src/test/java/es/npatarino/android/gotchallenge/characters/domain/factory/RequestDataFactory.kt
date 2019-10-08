package es.npatarino.android.gotchallenge.characters.domain.factory

import es.npatarino.android.gotchallenge.base.test.randomString
import es.npatarino.android.gotchallenge.characters.domain.model.CharacterRequest
import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter

class RequestDataFactory {
    companion object {
        fun makeRequest() =
                CharacterRequest(
                        randomString(),
                        randomString()
                )
    }
}