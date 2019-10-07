package es.npatarino.android.gotchallenge.characters.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.characters.presentation.viewmodel.GoTCharacterListViewModel
import kotlinx.android.synthetic.main.activity_character_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoTCharacterListActivity : AppCompatActivity() {

    private val characterListViewModel: GoTCharacterListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        check(intent.hasExtra(HOUSE_ID))
        check(intent.hasExtra(HOUSE_NAME))

        toolbar.title = intent.getStringExtra(HOUSE_NAME)
        setSupportActionBar(toolbar!!)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        characterListViewModel.houseId = intent.getStringExtra(HOUSE_ID)
        characterListViewModel.getCharacterList()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val HOUSE_ID = "houseId"
        private const val HOUSE_NAME = "houseName"

        fun startActivity(context: Context, houseId: String, houseName: String) {
            context.startActivity(
                    Intent(context, GoTCharacterListActivity::class.java).apply {
                        putExtra(HOUSE_ID, houseId)
                        putExtra(HOUSE_NAME, houseName)
                    }
            )
        }
    }
}