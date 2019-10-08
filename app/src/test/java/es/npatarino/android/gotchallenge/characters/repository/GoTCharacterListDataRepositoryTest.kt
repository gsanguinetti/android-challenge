package es.npatarino.android.gotchallenge.characters.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import es.npatarino.android.gotchallenge.data.GoTEntityDataFactory
import es.npatarino.android.gotchallenge.characters.domain.factory.GoTCharacterDataFactory
import es.npatarino.android.gotchallenge.data.GoTDataProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GoTCharacterListDataRepositoryTest {

    private lateinit var characterListDataRepository: GoTCharacterListDataRepository

    private lateinit var dataProvider: GoTDataProvider
    private lateinit var characterDataMapper: GoTCharacterDataMapper

    @Before
    fun before() {
        dataProvider = mock()
        characterDataMapper = mock()

        characterListDataRepository = GoTCharacterListDataRepository(dataProvider, characterDataMapper)
    }

    @Test
    fun repositoryShouldReturnsData() {
        val result = GoTEntityDataFactory.makeGoTCharacterEntity()
        val characters = GoTCharacterDataFactory.makeGoTCharacter()
        whenever(dataProvider.getGoTCharacters()).thenReturn(Single.just(listOf(result)))
        whenever(characterDataMapper.mapFromEntity(result)).thenReturn(characters)

        characterListDataRepository.getCharacterList()
                .test()
                .await()
                .assertNoErrors()
                .assertComplete()
                .assertValue { it.first() == characters }
    }

    @Test
    fun repositoryShouldReturnsErrorWhenCannotRetrieveData() {
        val exception = Exception()
        whenever(dataProvider.getGoTCharacters()).thenReturn(Single.error(exception))

        characterListDataRepository.getCharacterList()
                .test()
                .await()
                .assertError(exception)
    }
}