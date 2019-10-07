package es.npatarino.android.gotchallenge.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class GoTCharacterEntity(
        val description: String,
        @ColumnInfo(name = "house_id") val houseId: String,
        @ColumnInfo(name = "house_image_url") val houseImageUrl: String,
        @ColumnInfo(name = "house_name") val houseName: String,
        @ColumnInfo(name = "image_url") val imageUrl: String,
        @PrimaryKey val name: String
)