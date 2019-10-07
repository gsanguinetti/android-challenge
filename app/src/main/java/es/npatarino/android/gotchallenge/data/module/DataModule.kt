package es.npatarino.android.gotchallenge.data.module

import androidx.room.Room
import es.npatarino.android.gotchallenge.BuildConfig
import es.npatarino.android.gotchallenge.data.GoTDataProvider
import es.npatarino.android.gotchallenge.data.datasource.local.GoTCharacterDatabase
import es.npatarino.android.gotchallenge.data.datasource.local.GoTCharactersLocalDataSource
import es.npatarino.android.gotchallenge.data.datasource.network.GoTCharactersNetworkDataSource
import es.npatarino.android.gotchallenge.data.datasource.network.NetworkService
import es.npatarino.android.gotchallenge.data.datasource.network.model.ServerAddress
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    factory { ServerAddress(BuildConfig.SERVER_BASE_URL) }
    factory { GoTDataProvider(get(), get()) }
    factory { GoTCharactersLocalDataSource(get()) }
    single { GoTCharactersNetworkDataSource(get()) }
    factory { NetworkService(get()) }
    single { Room.databaseBuilder(androidContext(), GoTCharacterDatabase::class.java, "characters").build() }
}