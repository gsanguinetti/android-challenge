package es.npatarino.android.gotchallenge.houses.repository

import android.util.Log
import es.npatarino.android.gotchallenge.data.GoTDataProvider
import es.npatarino.android.gotchallenge.houses.domain.model.GoTHouse
import es.npatarino.android.gotchallenge.houses.domain.repository.GoTHousesRepository
import io.reactivex.Single

class GoTHousesDataRepository(
        private val dataProvider: GoTDataProvider,
        private val housesDataMapper: GoTHousesDataMapper) : GoTHousesRepository {

    override fun getHouses(): Single<List<GoTHouse>> =
            dataProvider.getGoTCharacters()
                    .map { it ->
                        //Filtering null HouseIds. In production projects this line should track an non critical exception
                        it.filter {
                            it.houseId.isNotEmpty()
                                    .apply { if(!this) Log.d(LOGGER_TAG, "HouseId null: $it") } }
                                //Filtering null House names. In production projects this line should track an non critical exception
                                .filter {
                                    it.houseName.isNotEmpty()
                                            .apply { if(!this) Log.d(LOGGER_TAG, "House name empty: $it") } }
                                .groupBy { it.houseId }
                                .values
                                .map { characters -> housesDataMapper.mapFromEntity(characters.first()) }
                    }

    companion object {
        private const val LOGGER_TAG = "GoTHousesDataRepository"
    }
}