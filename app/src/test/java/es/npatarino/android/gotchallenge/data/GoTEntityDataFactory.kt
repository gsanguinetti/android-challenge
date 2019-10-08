package es.npatarino.android.gotchallenge.data

import es.npatarino.android.gotchallenge.base.test.randomString
import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity

class GoTEntityDataFactory {
    companion object {
        fun makeGoTCharacterEntity(): GoTCharacterEntity =
                GoTCharacterEntity(
                        randomString(),
                        randomString(),
                        randomString(),
                        randomString(),
                        randomString(),
                        randomString()
                )
    }
}