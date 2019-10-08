package es.npatarino.android.gotchallenge.characters.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.base.presentation.loadImage
import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter
import kotlinx.android.synthetic.main.activity_character_detail.*
import kotlinx.android.synthetic.main.activity_character_detail.toolbar

class GoTCharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        check(intent.hasExtra(CHARACTER_EXTRA))

        intent.getParcelableExtra<GoTCharacter>(CHARACTER_EXTRA).let {
            ivPhoto.loadImage(it.imageUrl)
            tvName.text = it.name
            tvDescription.text = it.description

            toolbar.title = it.name
            setSupportActionBar(toolbar!!)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        configureParallaxEffect()
    }

    private fun configureParallaxEffect() {
        svScrollContent.onScrollChanged = {
            ivPhoto.translationY = it * PARALLAX_EFFECT_FACTOR
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        private const val CHARACTER_EXTRA = "characterExtra"
        private const val PARALLAX_EFFECT_FACTOR = 0.5f

        fun startActivity(activity: AppCompatActivity,
                          sharedElementTransitionViews :List<androidx.core.util.Pair<View, String>>,
                          character :GoTCharacter) {

            activity.startActivity(
                Intent(activity, GoTCharacterDetailActivity::class.java).apply {
                    putExtra(CHARACTER_EXTRA, character)
                }, ActivityOptionsCompat.
                    makeSceneTransitionAnimation(activity, *sharedElementTransitionViews.toTypedArray())
                    .toBundle()
            )
        }
    }
}
