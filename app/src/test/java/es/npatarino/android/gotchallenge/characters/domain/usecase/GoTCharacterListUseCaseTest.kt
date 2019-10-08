package es.npatarino.android.gotchallenge.characters.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.npatarino.android.gotchallenge.base.test.randomString
import es.npatarino.android.gotchallenge.characters.domain.factory.GoTCharacterDataFactory
import es.npatarino.android.gotchallenge.characters.domain.repository.GoTCharacterListRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GoTCharacterListUseCaseTest {

    private lateinit var characterListUseCase: GoTCharacterListUseCase

    private lateinit var characterListRepository: GoTCharacterListRepository

    @Before
    fun before() {
        characterListRepository = mock()
        characterListUseCase = GoTCharacterListUseCase(characterListRepository)
    }

    @Test
    fun useCaseObservableShouldCallRepository() {
        whenever(characterListRepository.getCharacterList()).thenReturn(
                Single.just(listOf(GoTCharacterDataFactory.makeGoTCharacter())))

        characterListUseCase.buildUseCaseObservable()
                .blockingGet()
        verify(characterListRepository).getCharacterList()
    }

    @Test
    fun useCaseObservableShouldFilterHouseId() {
        var houseId = randomString()
        var validResult = GoTCharacterDataFactory.makeGoTCharacter(houseId)
        var results = listOf(GoTCharacterDataFactory.makeGoTCharacter(), validResult)

        whenever(characterListRepository.getCharacterList()).thenReturn(
                Single.just(results))

        characterListUseCase.buildUseCaseObservable(houseId)
                .test()
                .await()
                .assertNoErrors()
                .assertValue { it.size == 1 }
                .assertValue { it.first() == validResult }
    }

    @Test
    fun useCaseObservableShouldNotFilterHouseId() {
        var results = listOf(GoTCharacterDataFactory.makeGoTCharacter())

        whenever(characterListRepository.getCharacterList()).thenReturn(
                Single.just(results))

        characterListUseCase.buildUseCaseObservable(randomString())
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

        characterListUseCase.buildUseCaseObservable()
                .test()
                .await()
                .assertError(exception)
    }
}