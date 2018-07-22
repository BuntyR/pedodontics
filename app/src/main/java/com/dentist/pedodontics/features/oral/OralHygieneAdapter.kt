package com.dentist.pedodontics.features.oral

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dentist.pedodontics.R
import com.dentist.pedodontics.data.models.response.oral.DataItem
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.rv_item_oral.view.*
import javax.inject.Inject

class OralHygieneAdapter @Inject constructor() : RecyclerView.Adapter<OralHygieneAdapter.OralHygieneViewHolder>() {

  private var mData: ArrayList<DataItem>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OralHygieneViewHolder {
    return OralHygieneViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.rv_item_oral,
            parent, false))
  }

  override fun getItemCount(): Int {
    return mData?.size ?: 0
  }

  fun setData(data: ArrayList<DataItem>?) {
    Logger.d("Data in OralHygiene Adapter : " + Gson().toJson(data))
    if (mData == null) {
      mData = arrayListOf()
    }
    this.mData?.clear()
    if (data != null) {
      mData?.addAll(data)
    }
    notifyDataSetChanged()
  }

  override fun onBindViewHolder(holder: OralHygieneViewHolder, position: Int) {
    val checking = this.mData?.get(position)
    Logger.d("Checking Data : $checking")
    holder.bindTo(checking!!)
  }

  class OralHygieneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(dataItem: DataItem) {
      itemView.age.setText(dataItem.age)
      itemView.stageValue.setText(dataItem.developmentalStage)
      itemView.methodValue.setText(dataItem.oralHygieneMethod)
      itemView.frequency.setText(dataItem.toothBrushingFrequency)
      itemView.brushingMethod.setText(dataItem.toothBrushingMethod)
      itemView.pasteQuantity.setText(dataItem.toothPasteQuantity)
      itemView.toothbrushType.setText(dataItem.typeOfToothBrush)
      itemView.toothPasteType.setText(dataItem.typeOfToothPaste)
    }
  }
}