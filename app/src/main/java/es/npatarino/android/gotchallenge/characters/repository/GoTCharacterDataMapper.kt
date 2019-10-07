package es.npatarino.android.gotchallenge.characters.repository

import es.npatarino.android.gotchallenge.base.data.DataMapper
import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter
import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity

class GoTCharacterDataMapper : DataMapper<GoTCharacterEntity, GoTCharacter> {

    override fun mapFromEntity(entity: GoTCharacterEntity): GoTCharacter =
            GoTCharacter(entity.name, entity.imageUrl, entity.houseId, entity.description)

    override fun mapToEntity(domainModel: GoTCharacter): GoTCharacterEntity {
        throw NotImplementedError()
    }
}