package es.npatarino.android.gotchallenge.characters.repository.mapper

import es.npatarino.android.gotchallenge.data.GoTEntityDataFactory
import es.npatarino.android.gotchallenge.characters.repository.GoTCharacterDataMapper
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GoTCharacterDataMapperTest {

    private lateinit var goTCharacterDataMapper: GoTCharacterDataMapper

    @Before
    fun before() {
        goTCharacterDataMapper = GoTCharacterDataMapper()
    }

    @Test
    fun mapToCharacter() {
        val entity = GoTEntityDataFactory.makeGoTCharacterEntity()
        goTCharacterDataMapper.mapFromEntity(entity).run {
            assertEquals(entity.houseId, houseId)
            assertEquals(entity.name, name)
            assertEquals(entity.description, description)
            assertEquals(entity.imageUrl, imageUrl)
        }
    }
}