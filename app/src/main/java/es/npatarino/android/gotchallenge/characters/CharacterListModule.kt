package es.npatarino.android.gotchallenge.characters

import es.npatarino.android.gotchallenge.characters.domain.repository.GoTCharacterListRepository
import es.npatarino.android.gotchallenge.characters.domain.usecase.FindGoTCharacterUseCase
import es.npatarino.android.gotchallenge.characters.domain.usecase.GoTCharacterListUseCase
import es.npatarino.android.gotchallenge.characters.presentation.ui.GoTCharacterListAdapter
import es.npatarino.android.gotchallenge.characters.presentation.viewmodel.GoTCharacterListViewModel
import es.npatarino.android.gotchallenge.characters.repository.GoTCharacterDataMapper
import es.npatarino.android.gotchallenge.characters.repository.GoTCharacterListDataRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterListModule = module {

    // Presentation layer injections
    viewModel { GoTCharacterListViewModel(get(), get()) }
    factory { GoTCharacterListAdapter() }

    // Domain layer injections
    factory { FindGoTCharacterUseCase(get()) }
    factory { GoTCharacterListUseCase(get()) }

    // Repository layer injections
    factory { GoTCharacterListDataRepository(get(), get()) as GoTCharacterListRepository }
    factory { GoTCharacterDataMapper() }
}