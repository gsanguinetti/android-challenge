package es.npatarino.android.gotchallenge.characters.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import es.npatarino.android.gotchallenge.characters.domain.factory.GoTCharacterDataFactory
import es.npatarino.android.gotchallenge.characters.domain.factory.RequestDataFactory
import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter
import es.npatarino.android.gotchallenge.characters.domain.usecase.FindGoTCharacterUseCase
import es.npatarino.android.gotchallenge.characters.domain.usecase.GoTCharacterListUseCase
import es.npatarino.android.gotchallenge.characters.presentation.model.GoTCharacterListUiState
import es.npatarino.android.gotchallenge.characters.presentation.viewmodel.GoTCharacterListViewModel
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GoTCharacterListViewModelTest {

    private lateinit var viewModel: GoTCharacterListViewModel

    private lateinit var characterListUseCase: GoTCharacterListUseCase
    private lateinit var findCharacterUseCase: FindGoTCharacterUseCase

    private lateinit var findCharacterExecutorCaptor:
            KArgumentCaptor<DisposableSingleObserver<List<GoTCharacter>>>
    private lateinit var getCharactersExecutorCaptor:
            KArgumentCaptor<DisposableSingleObserver<List<GoTCharacter>>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        characterListUseCase = mock()
        findCharacterUseCase = mock()

        viewModel = GoTCharacterListViewModel(
                characterListUseCase,
                findCharacterUseCase
        )

        findCharacterExecutorCaptor = argumentCaptor()
        getCharactersExecutorCaptor = argumentCaptor()
    }

    @Test
    fun callViewModelShouldExecuteUseCase() {
        viewModel.getCharacterList()
        verify(characterListUseCase).execute(getCharactersExecutorCaptor.capture(), eq(""))

        val characterRequest = RequestDataFactory.makeRequest()
        viewModel.houseId = characterRequest.houseId
        viewModel.getCharacterList(characterRequest.query)
        verify(findCharacterUseCase).execute(findCharacterExecutorCaptor.capture(), eq(characterRequest))
    }

    @Test
    fun callViewModelOnStartShouldShowLoadingState() {
        viewModel.getCharacterList()
        verify(characterListUseCase).execute(getCharactersExecutorCaptor.capture(), eq(""))
        getCharactersExecutorCaptor.firstValue.onSubscribe(getCharactersExecutorCaptor.firstValue)
        Assert.assertEquals(GoTCharacterListUiState.Loading, viewModel.characterListUiState.value)
    }

    @Test
    fun callViewModelShouldLoadsData() {
        val result = listOf(GoTCharacterDataFactory.makeGoTCharacter())
        viewModel.getCharacterList("")

        verify(characterListUseCase).execute(getCharactersExecutorCaptor.capture(), eq(""))
        getCharactersExecutorCaptor.firstValue.onSuccess(result)

        assert(viewModel.characterListUiState.value is GoTCharacterListUiState.Data)
        assert((viewModel.characterListUiState.value as GoTCharacterListUiState.Data).characters ==
                result)
    }

    @Test
    fun callViewModelShouldShowErrorOnErrorState() {
        viewModel.getCharacterList()
        verify(characterListUseCase).execute(getCharactersExecutorCaptor.capture(), eq(""))
        getCharactersExecutorCaptor.firstValue.onError(Exception())

        assert(viewModel.characterListUiState.value is GoTCharacterListUiState.Error)
    }
}