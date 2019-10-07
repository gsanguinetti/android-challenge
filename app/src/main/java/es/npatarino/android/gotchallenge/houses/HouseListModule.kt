package es.npatarino.android.gotchallenge.houses

import es.npatarino.android.gotchallenge.houses.domain.repository.GoTHousesRepository
import es.npatarino.android.gotchallenge.houses.domain.usecase.GoTHousesUseCase
import es.npatarino.android.gotchallenge.houses.presentation.ui.GoTHouseAdapter
import es.npatarino.android.gotchallenge.houses.presentation.viewmodel.GoTHousesViewModel
import es.npatarino.android.gotchallenge.houses.repository.GoTHousesDataMapper
import es.npatarino.android.gotchallenge.houses.repository.GoTHousesDataRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val houseListModule = module {

    // Presentation layer injections
    viewModel { GoTHousesViewModel(get()) }
    factory { GoTHouseAdapter() }

    // Domain layer injections
    factory { GoTHousesUseCase(get()) }

    // Repository layer injections
    factory { GoTHousesDataRepository(get(), get()) as GoTHousesRepository }
    factory { GoTHousesDataMapper() }
}