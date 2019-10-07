package es.npatarino.android.gotchallenge.home.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.characters.presentation.viewmodel.GoTCharacterListViewModel
import es.npatarino.android.gotchallenge.houses.presentation.viewmodel.GoTHousesViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity() {

    private val housesViewModel: GoTHousesViewModel by viewModel()
    private val characterListViewModel : GoTCharacterListViewModel by viewModel()
    private val sectionsPagerAdapter :SectionsPagerAdapter by inject {
        parametersOf(this, supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        vpContainer.adapter = sectionsPagerAdapter
        tlTabs.setupWithViewPager(vpContainer)

        housesViewModel.getHouses()
        characterListViewModel.getCharacterList()
    }
}
