package es.npatarino.android.gotchallenge.home

import android.content.Context
import androidx.fragment.app.FragmentManager
import es.npatarino.android.gotchallenge.home.presentation.ui.SectionsPagerAdapter
import org.koin.dsl.module

val homeModule = module {

    //Presentation layer injections
    factory { (context : Context, fragmentManager : FragmentManager) ->
        SectionsPagerAdapter(context, fragmentManager) }
}