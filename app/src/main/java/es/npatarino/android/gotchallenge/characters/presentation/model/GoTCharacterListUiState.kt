package es.npatarino.android.gotchallenge.characters.presentation.model

import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter

sealed class GoTCharacterListUiState {
    object Error : GoTCharacterListUiState()
    object Loading : GoTCharacterListUiState()
    class Data(val characters: List<GoTCharacter>) : GoTCharacterListUiState()
}