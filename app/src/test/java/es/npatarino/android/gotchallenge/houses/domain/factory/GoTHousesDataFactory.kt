package es.npatarino.android.gotchallenge.houses.domain.factory

import es.npatarino.android.gotchallenge.base.test.randomString
import es.npatarino.android.gotchallenge.houses.domain.model.GoTHouse

class GoTHousesDataFactory {
    companion object {
        fun makeGoTHouse() =
                GoTHouse(
                        randomString(),
                        randomString(),
                        randomString()
                )
    }
}