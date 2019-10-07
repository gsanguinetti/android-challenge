package es.npatarino.android.gotchallenge.home.presentation.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.characters.presentation.ui.GoTCharacterListFragment
import es.npatarino.android.gotchallenge.houses.presentation.ui.GoTHousesListFragment

class SectionsPagerAdapter(
        private val context: Context,
        fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) GoTCharacterListFragment()
        else GoTHousesListFragment()
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? =
        context.getString( when (position) {
            0 -> R.string.characters
            else -> R.string.houses
        })
}