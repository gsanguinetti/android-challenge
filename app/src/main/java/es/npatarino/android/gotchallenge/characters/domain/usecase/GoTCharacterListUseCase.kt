package es.npatarino.android.gotchallenge.characters.domain.usecase

import es.npatarino.android.gotchallenge.base.domain.SingleUseCase
import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter
import es.npatarino.android.gotchallenge.characters.domain.repository.GoTCharacterListRepository
import io.reactivex.Single

class GoTCharacterListUseCase(
        private val characterListRepository: GoTCharacterListRepository
) : SingleUseCase<List<GoTCharacter>, String>() {

    override fun buildUseCaseObservable(params: String?): Single<List<GoTCharacter>> =
            characterListRepository.getCharacterList().setUpForUseCase()
                    .map { list -> if(params.isNullOrEmpty()) list else list.filter { it.houseId == params } }
}