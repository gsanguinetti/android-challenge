package es.npatarino.android.gotchallenge.characters.domain.usecase

import es.npatarino.android.gotchallenge.base.domain.SingleUseCase
import es.npatarino.android.gotchallenge.characters.domain.model.CharacterRequest
import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter
import es.npatarino.android.gotchallenge.characters.domain.repository.GoTCharacterListRepository
import io.reactivex.Single

class FindGoTCharacterUseCase(
        private val characterListRepository: GoTCharacterListRepository
) : SingleUseCase<List<GoTCharacter>, CharacterRequest>() {

    public override fun buildUseCaseObservable(params: CharacterRequest?): Single<List<GoTCharacter>> {
        check(params != null)
        return if(params.query.isBlank())
            characterListRepository.getCharacterList()
                    .setUpForUseCase().map { it.filterHouseId(params.houseId) }
        else characterListRepository.getCharacterList()
                .map { list -> list.filterHouseId(params.houseId) }
                .map { list -> list.filter { it.name.contains(params.query, true) } }
                .setUpForUseCase()
    }

    private fun List<GoTCharacter>.filterHouseId(houseId :String) :List<GoTCharacter> =
            if(houseId.isBlank()) this else this.filter { it.houseId == houseId }
}