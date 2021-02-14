package br.com.vicentec12.eventtest.interfaces

import android.view.View

fun interface OnItemClickListener {

    fun onItemClick(mView: View, mItem: Any?, mPosition: Int)

}