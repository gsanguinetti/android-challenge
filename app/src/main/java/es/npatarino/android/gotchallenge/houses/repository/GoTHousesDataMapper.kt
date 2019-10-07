package es.npatarino.android.gotchallenge.houses.repository

import es.npatarino.android.gotchallenge.base.data.DataMapper
import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity
import es.npatarino.android.gotchallenge.houses.domain.model.GoTHouse

class GoTHousesDataMapper : DataMapper<GoTCharacterEntity, GoTHouse> {

    override fun mapFromEntity(entity: GoTCharacterEntity): GoTHouse =
            GoTHouse(entity.houseId, entity.houseImageUrl, entity.houseName)

    override fun mapToEntity(domainModel: GoTHouse): GoTCharacterEntity {
        throw NotImplementedError()
    }
}