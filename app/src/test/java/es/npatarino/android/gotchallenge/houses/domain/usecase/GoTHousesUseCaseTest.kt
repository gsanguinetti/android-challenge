package es.npatarino.android.gotchallenge.houses.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.npatarino.android.gotchallenge.houses.domain.factory.GoTHousesDataFactory
import es.npatarino.android.gotchallenge.houses.domain.repository.GoTHousesRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GoTHousesUseCaseTest {

    private lateinit var housesUseCase: GoTHousesUseCase

    private lateinit var housesRepository: GoTHousesRepository

    @Before
    fun before() {
        housesRepository = mock()
        housesUseCase = GoTHousesUseCase(housesRepository)
    }

    @Test
    fun useCaseObservableShouldCallRepository() {
        whenever(housesRepository.getHouses()).thenReturn(
                Single.just(listOf(GoTHousesDataFactory.makeGoTHouse())))

        housesUseCase.buildUseCaseObservable()
                .blockingGet()
        verify(housesRepository).getHouses()
    }

    @Test
    fun useCaseObservableShouldGetHouse() {
        var house = GoTHousesDataFactory.makeGoTHouse()

        whenever(housesRepository.getHouses()).thenReturn(
                Single.just(listOf(house)))

        housesUseCase.buildUseCaseObservable()
                .test()
                .await()
                .assertNoErrors()
                .assertValue { it.size == 1 }
                .assertValue { it.first() == house }
    }

    @Test
    fun useCaseObservableShouldThrowExceptionOnRepositoryError() {
        var exception = Exception()
        whenever(housesRepository.getHouses()).thenReturn(
                Single.error(exception))

        housesUseCase.buildUseCaseObservable()
                .test()
                .await()
                .assertError(exception)
    }
}