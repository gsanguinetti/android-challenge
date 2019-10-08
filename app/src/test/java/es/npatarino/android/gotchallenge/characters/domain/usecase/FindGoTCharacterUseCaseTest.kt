package es.npatarino.android.gotchallenge.characters.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.npatarino.android.gotchallenge.characters.domain.factory.GoTCharacterDataFactory
import es.npatarino.android.gotchallenge.characters.domain.factory.RequestDataFactory
import es.npatarino.android.gotchallenge.characters.domain.repository.GoTCharacterListRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FindGoTCharacterUseCaseTest {

    private lateinit var findGoTCharacterUseCase: FindGoTCharacterUseCase

    private lateinit var characterListRepository: GoTCharacterListRepository

    @Before
    fun before() {
        characterListRepository = mock()
        findGoTCharacterUseCase = FindGoTCharacterUseCase(characterListRepository)
    }

    @Test
    fun useCaseObservableShouldCallRepository() {
        whenever(characterListRepository.getCharacterList()).thenReturn(
                Single.just(listOf(GoTCharacterDataFactory.makeGoTCharacter())))

        findGoTCharacterUseCase.buildUseCaseObservable(RequestDataFactory.makeRequest())
                .blockingGet()
        verify(characterListRepository).getCharacterList()
    }

    @Test
    fun useCaseObservableShouldFindCharacter() {
        var request = RequestDataFactory.makeRequest()
        var validResult = GoTCharacterDataFactory.makeGoTCharacter(request.houseId, request.query)
        var results = listOf(GoTCharacterDataFactory.makeGoTCharacter(), validResult)

        whenever(characterListRepository.getCharacterList()).thenReturn(
                Single.just(results))

        findGoTCharacterUseCase.buildUseCaseObservable(request)
                .test()
                .await()
                .assertNoErrors()
                .assertValue { it.size == 1 }
                .assertValue { it.first() == validResult }
    }

    @Test
    fun useCaseObservableShouldNotFindCharacter() {
        var results = listOf(GoTCharacterDataFactory.makeGoTCharacter())

        whenever(characterListRepository.getCharacterList()).thenReturn(
                Single.just(results))

        findGoTCharacterUseCase.buildUseCaseObservable(RequestDataFactory.makeRequest())
                .test()
                .await()
                .assertNoErrors()
                .assertValue { it.isEmpty() }
    }

    @Test
    fun useCaseObservableShouldThrowExceptionOnRepositoryError() {
        var exception = Exception()
        whenever(characterListRepository.getCharacterList()).thenReturn(
                Single.error(exception))

        findGoTCharacterUseCase.buildUseCaseObservable(RequestDataFactory.makeRequest())
                .test()
                .await()
                .assertError(exception)
    }
}