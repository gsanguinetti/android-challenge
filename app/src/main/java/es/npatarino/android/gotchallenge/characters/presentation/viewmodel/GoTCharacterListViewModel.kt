package es.npatarino.android.gotchallenge.characters.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.npatarino.android.gotchallenge.characters.domain.model.CharacterRequest
import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter
import es.npatarino.android.gotchallenge.characters.domain.usecase.FindGoTCharacterUseCase
import es.npatarino.android.gotchallenge.characters.domain.usecase.GoTCharacterListUseCase
import es.npatarino.android.gotchallenge.characters.presentation.model.GoTCharacterListUiState
import io.reactivex.observers.DisposableSingleObserver

class GoTCharacterListViewModel(
        private val characterListUseCase: GoTCharacterListUseCase,
        private val findCharacterUseCase: FindGoTCharacterUseCase
) : ViewModel() {

    val characterListUiState = MutableLiveData<GoTCharacterListUiState>()
    var houseId :String = ""

    fun getCharacterList(query: String = "") {

        if (query.isEmpty() || query.isBlank())
            characterListUseCase.execute(object : DisposableSingleObserver<List<GoTCharacter>>() {
                override fun onStart() =
                        characterListUiState.postValue(GoTCharacterListUiState.Loading)

                override fun onSuccess(t: List<GoTCharacter>) =
                        characterListUiState.postValue(GoTCharacterListUiState.Data(t))

                override fun onError(e: Throwable) =
                        characterListUiState.postValue(GoTCharacterListUiState.Error)
            }, this.houseId)
        else {
            characterListUseCase.clear()
            findCharacterUseCase.execute(object : DisposableSingleObserver<List<GoTCharacter>>() {
                override fun onSuccess(t: List<GoTCharacter>) =
                        characterListUiState.postValue(GoTCharacterListUiState.Data(t))

                override fun onError(e: Throwable) =
                        characterListUiState.postValue(GoTCharacterListUiState.Error)
            }, CharacterRequest(houseId, query))
        }
    }
}