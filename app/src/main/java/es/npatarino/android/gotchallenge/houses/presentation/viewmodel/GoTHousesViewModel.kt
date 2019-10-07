package es.npatarino.android.gotchallenge.houses.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.npatarino.android.gotchallenge.houses.domain.model.GoTHouse
import es.npatarino.android.gotchallenge.houses.domain.usecase.GoTHousesUseCase
import es.npatarino.android.gotchallenge.houses.presentation.model.GoTHouseListUiState
import io.reactivex.observers.DisposableSingleObserver

class GoTHousesViewModel(
        private val housesUseCase: GoTHousesUseCase
) : ViewModel() {

    val houseListUiState = MutableLiveData<GoTHouseListUiState>()

    fun getHouses() {
        housesUseCase.execute(object : DisposableSingleObserver<List<GoTHouse>>() {
            override fun onStart() = houseListUiState.postValue(GoTHouseListUiState.Loading)
            override fun onSuccess(t: List<GoTHouse>) = houseListUiState.postValue(GoTHouseListUiState.Data(t))
            override fun onError(e: Throwable) = houseListUiState.postValue(GoTHouseListUiState.Error)
        })
    }
}