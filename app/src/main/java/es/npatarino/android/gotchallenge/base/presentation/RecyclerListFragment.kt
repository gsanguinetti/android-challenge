package es.npatarino.android.gotchallenge.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.npatarino.android.gotchallenge.R
import kotlinx.android.synthetic.main.fragment_list.*

abstract class RecyclerListFragment<ADAPTER : RecyclerView.Adapter<*>> : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvContentList.layoutManager = LinearLayoutManager(activity)
        rvContentList.setHasFixedSize(true)
        rvContentList.adapter = getAdapter()
    }

    abstract fun getAdapter(): ADAPTER
}