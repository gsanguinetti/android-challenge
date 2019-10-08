package es.npatarino.android.gotchallenge.houses.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import es.npatarino.android.gotchallenge.houses.domain.factory.GoTHousesDataFactory
import es.npatarino.android.gotchallenge.houses.domain.model.GoTHouse
import es.npatarino.android.gotchallenge.houses.domain.usecase.GoTHousesUseCase
import es.npatarino.android.gotchallenge.houses.presentation.model.GoTHouseListUiState
import es.npatarino.android.gotchallenge.houses.presentation.viewmodel.GoTHousesViewModel
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GoTHousesViewModelTest {

    private lateinit var viewModel: GoTHousesViewModel

    private lateinit var housesUseCase: GoTHousesUseCase

    private lateinit var getHousesExecutorCaptor:
            KArgumentCaptor<DisposableSingleObserver<List<GoTHouse>>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        housesUseCase = mock()
        viewModel = GoTHousesViewModel(housesUseCase)
        getHousesExecutorCaptor = argumentCaptor()
    }

    @Test
    fun callViewModelShouldExecuteUseCase() {
        viewModel.getHouses()
        verify(housesUseCase).execute(getHousesExecutorCaptor.capture(), eq(null))
    }

    @Test
    fun callViewModelOnStartShouldShowLoadingState() {
        viewModel.getHouses()
        verify(housesUseCase).execute(getHousesExecutorCaptor.capture(), eq(null))
        getHousesExecutorCaptor.firstValue.onSubscribe(getHousesExecutorCaptor.firstValue)
        Assert.assertEquals(GoTHouseListUiState.Loading, viewModel.houseListUiState.value)
    }

    @Test
    fun callViewModelShouldLoadsData() {
        val result = listOf(GoTHousesDataFactory.makeGoTHouse())
        viewModel.getHouses()

        verify(housesUseCase).execute(getHousesExecutorCaptor.capture(), eq(null))
        getHousesExecutorCaptor.firstValue.onSuccess(result)

        assert(viewModel.houseListUiState.value is GoTHouseListUiState.Data)
        assert((viewModel.houseListUiState.value as GoTHouseListUiState.Data).houses ==
                result)
    }

    @Test
    fun callViewModelShouldShowErrorOnErrorState() {
        viewModel.getHouses()
        verify(housesUseCase).execute(getHousesExecutorCaptor.capture(), eq(null))
        getHousesExecutorCaptor.firstValue.onError(Exception())

        assert(viewModel.houseListUiState.value is GoTHouseListUiState.Error)
    }
}