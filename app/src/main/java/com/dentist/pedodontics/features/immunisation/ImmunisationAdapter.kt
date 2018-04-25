package com.dentist.pedodontics.features.immunisation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dentist.pedodontics.R
import java.util.*
import javax.inject.Inject

class ImmunisationAdapter @Inject constructor() : RecyclerView.Adapter<ImmunisationAdapter.ImmunisationViewHolder>() {

  private var mData: List<Objects>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImmunisationViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_immunisation,
        parent,
        false)
    return ImmunisationViewHolder(view)
  }

  fun setData(mData: List<Objects>) {
    this.mData = mData
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int {
    return mData?.size ?: 0
  }

  override fun onBindViewHolder(holder: ImmunisationViewHolder, position: Int) {

  }

  class ImmunisationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  }
}