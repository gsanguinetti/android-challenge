package es.npatarino.android.gotchallenge.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.npatarino.android.gotchallenge.data.model.GoTCharacterEntity

@Database(entities = [GoTCharacterEntity::class], version = 1)
abstract class GoTCharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): GoTCharacterDao
}