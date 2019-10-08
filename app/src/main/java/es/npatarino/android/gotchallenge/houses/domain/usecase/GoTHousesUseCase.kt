package es.npatarino.android.gotchallenge.houses.domain.usecase

import es.npatarino.android.gotchallenge.base.domain.SingleUseCase
import es.npatarino.android.gotchallenge.houses.domain.model.GoTHouse
import es.npatarino.android.gotchallenge.houses.domain.repository.GoTHousesRepository
import io.reactivex.Single

class GoTHousesUseCase(
        private val housesRepository: GoTHousesRepository
) : SingleUseCase<List<GoTHouse>, Unit>() {

    public override fun buildUseCaseObservable(params: Unit?): Single<List<GoTHouse>> =
        housesRepository.getHouses().setUpForUseCase()
}