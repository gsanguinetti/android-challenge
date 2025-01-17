package es.npatarino.android.gotchallenge.base.presentation

import android.os.Handler
import androidx.appcompat.widget.SearchView


abstract class DelayedOnQueryTextListener : SearchView.OnQueryTextListener {

    private val handler = Handler()
    private var runnable: Runnable? = null

    override fun onQueryTextSubmit(s: String): Boolean = false

    override fun onQueryTextChange(s: String): Boolean {
        handler.removeCallbacks(runnable)
        runnable = Runnable { onDelayedQueryTextChange(s) }
        handler.postDelayed(runnable, DELAY_TIME_IN_MILLIS)
        return true
    }

    abstract fun onDelayedQueryTextChange(query: String)

    companion object {
        private const val DELAY_TIME_IN_MILLIS = 400L
    }
}