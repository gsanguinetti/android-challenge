package es.npatarino.android.gotchallenge.characters.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.google.android.material.snackbar.Snackbar
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.base.presentation.RecyclerListFragment
import es.npatarino.android.gotchallenge.base.presentation.hideView
import es.npatarino.android.gotchallenge.base.presentation.nonNullObserve
import es.npatarino.android.gotchallenge.base.presentation.showView
import es.npatarino.android.gotchallenge.characters.presentation.model.GoTCharacterListUiState
import es.npatarino.android.gotchallenge.characters.presentation.viewmodel.GoTCharacterListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GoTCharacterListFragment : RecyclerListFragment<GoTCharacterListAdapter>() {

    private val characterViewModel: GoTCharacterListViewModel by sharedViewModel()
    private val characterListAdapter: GoTCharacterListAdapter by inject()

    private var currentSearch = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterListAdapter.onQueryCharacter = { query ->
            characterViewModel.getCharacterList(query)
            currentSearch = query
        }

        characterListAdapter.onCharacterSelected = { character, ivBackground ->
            if (activity is AppCompatActivity) {
                GoTCharacterDetailActivity.startActivity(
                        activity as AppCompatActivity,
                        listOf(Pair(ivBackground as View, IMAGE_TRANSITION_KEY)),
                        character
                )
            }
        }

        characterViewModel.characterListUiState.nonNullObserve(this) {
            when (it) {
                is GoTCharacterListUiState.Loading -> {
                    rvContentList.hideView()
                    pbLoading.showView()
                }
                is GoTCharacterListUiState.Error -> {
                    rvContentList.showView()
                    pbLoading.hideView()
                    Snackbar.make(rvContentList, R.string.loading_content_error,
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.retry) { characterViewModel.getCharacterList(currentSearch) }
                            .show()
                }
                is GoTCharacterListUiState.Data -> {
                    rvContentList.showView()
                    pbLoading.hideView()
                    characterListAdapter.characters = it.characters
                }
            }
        }

        savedInstanceState?.let {
            characterViewModel.getCharacterList(it.getString(QUERY_EXTRA)!!)
        }
    }

    override fun getAdapter() = characterListAdapter

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(QUERY_EXTRA, currentSearch)
    }

    companion object {
        private const val QUERY_EXTRA = "queryExtra"
        private const val IMAGE_TRANSITION_KEY = "image"
    }
}