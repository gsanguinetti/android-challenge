package es.npatarino.android.gotchallenge.houses.domain.repository

import es.npatarino.android.gotchallenge.houses.domain.model.GoTHouse
import io.reactivex.Single

interface GoTHousesRepository {
    fun getHouses(): Single<List<GoTHouse>>
}