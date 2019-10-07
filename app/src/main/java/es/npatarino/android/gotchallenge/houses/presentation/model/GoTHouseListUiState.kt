package es.npatarino.android.gotchallenge.houses.presentation.model

import es.npatarino.android.gotchallenge.houses.domain.model.GoTHouse

sealed class GoTHouseListUiState {
    object Error : GoTHouseListUiState()
    object Loading : GoTHouseListUiState()
    class Data(val houses: List<GoTHouse>) : GoTHouseListUiState()
}