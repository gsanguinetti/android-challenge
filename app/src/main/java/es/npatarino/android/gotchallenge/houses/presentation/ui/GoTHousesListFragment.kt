package es.npatarino.android.gotchallenge.houses.presentation.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.base.presentation.RecyclerListFragment
import es.npatarino.android.gotchallenge.base.presentation.hideView
import es.npatarino.android.gotchallenge.base.presentation.nonNullObserve
import es.npatarino.android.gotchallenge.base.presentation.showView
import es.npatarino.android.gotchallenge.characters.presentation.ui.GoTCharacterListActivity
import es.npatarino.android.gotchallenge.houses.presentation.model.GoTHouseListUiState
import es.npatarino.android.gotchallenge.houses.presentation.viewmodel.GoTHousesViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GoTHousesListFragment : RecyclerListFragment<GoTHouseAdapter>() {

    private val housesViewModel: GoTHousesViewModel by sharedViewModel()
    private val housesAdapter: GoTHouseAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        housesAdapter.onHouseSelected = { houseId, houseName ->
            context?.let { GoTCharacterListActivity.startActivity(it, houseId, houseName) }
        }

        housesViewModel.houseListUiState.nonNullObserve(this) {
            when (it) {
                is GoTHouseListUiState.Loading -> {
                    rvContentList.hideView()
                    pbLoading.showView()
                }
                is GoTHouseListUiState.Error -> {
                    rvContentList.showView()
                    pbLoading.hideView()
                    Snackbar.make(rvContentList, R.string.loading_content_error,
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.retry) { housesViewModel.getHouses() }
                            .show()
                }
                is GoTHouseListUiState.Data -> {
                    rvContentList.showView()
                    pbLoading.hideView()
                    housesAdapter.houses = it.houses
                }
            }
        }
    }

    override fun getAdapter() = housesAdapter
}