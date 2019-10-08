package es.npatarino.android.gotchallenge.houses.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import es.npatarino.android.gotchallenge.data.GoTDataProvider
import es.npatarino.android.gotchallenge.data.GoTEntityDataFactory
import es.npatarino.android.gotchallenge.houses.domain.factory.GoTHousesDataFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GoTHousesRepositoryTest {

    private lateinit var housesDataRepository: GoTHousesDataRepository

    private lateinit var dataProvider: GoTDataProvider
    private lateinit var characterDataMapper: GoTHousesDataMapper

    @Before
    fun before() {
        dataProvider = mock()
        characterDataMapper = mock()

        housesDataRepository = GoTHousesDataRepository(dataProvider, characterDataMapper)
    }

    @Test
    fun repositoryShouldReturnsData() {
        val result = GoTEntityDataFactory.makeGoTCharacterEntity()
        val house = GoTHousesDataFactory.makeGoTHouse()
        whenever(dataProvider.getGoTCharacters()).thenReturn(Single.just(listOf(result)))
        whenever(characterDataMapper.mapFromEntity(result)).thenReturn(house)

        housesDataRepository.getHouses()
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .assertValue { it.first() == house }
    }

    @Test
    fun repositoryShouldReturnsErrorWhenCannotRetrieveData() {
        val exception = Exception()
        whenever(dataProvider.getGoTCharacters()).thenReturn(Single.error(exception))

        housesDataRepository.getHouses()
                .test()
                .await()
                .assertError(exception)
    }
}