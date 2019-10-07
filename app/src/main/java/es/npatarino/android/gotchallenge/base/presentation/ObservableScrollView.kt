package es.npatarino.android.gotchallenge.base.presentation

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

class ObservableScrollView : ScrollView {

    var onScrollChanged: ((deltaY: Int) -> Unit)? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        onScrollChanged?.invoke(t)
    }
}