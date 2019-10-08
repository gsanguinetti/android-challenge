package es.npatarino.android.gotchallenge.houses.repository.mapper

import es.npatarino.android.gotchallenge.data.GoTEntityDataFactory
import es.npatarino.android.gotchallenge.houses.repository.GoTHousesDataMapper
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GoTHousesDataMapperTest {

    private lateinit var housesDataMapper: GoTHousesDataMapper

    @Before
    fun before() {
        housesDataMapper = GoTHousesDataMapper()
    }

    @Test
    fun mapToHouse() {
        val entity = GoTEntityDataFactory.makeGoTCharacterEntity()
        housesDataMapper.mapFromEntity(entity).run {
            assertEquals(entity.houseId, houseId)
            assertEquals(entity.houseName, houseName)
            assertEquals(entity.houseImageUrl, houseImageUrl)
        }
    }
}