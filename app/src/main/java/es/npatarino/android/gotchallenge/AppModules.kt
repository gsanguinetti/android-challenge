package es.npatarino.android.gotchallenge

import es.npatarino.android.gotchallenge.characters.characterListModule
import es.npatarino.android.gotchallenge.data.module.dataModule
import es.npatarino.android.gotchallenge.home.homeModule
import es.npatarino.android.gotchallenge.houses.houseListModule

val appModules = listOf(
        dataModule,
        characterListModule,
        houseListModule,
        homeModule
)