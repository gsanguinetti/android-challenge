package es.npatarino.android.gotchallenge.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GoTCharacterDao {

    @Query("SELECT * FROM characters")
    fun getAll(): Single<List<GoTCharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg characterEntities: GoTCharacterEntity) : List<Long>

    @Query("DELETE FROM characters")
    fun deleteAll() : Int
}