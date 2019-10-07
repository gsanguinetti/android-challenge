package es.npatarino.android.gotchallenge.characters.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoTCharacter(
        val name: String,
        val imageUrl: String,
        val houseId: String,
        val description: String
) : Parcelable