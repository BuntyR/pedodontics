package com.dentist.pedodontics.features.immunisation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dentist.pedodontics.R
import com.dentist.pedodontics.data.models.response.immunisation.ImmunisationData
import com.dentist.pedodontics.data.models.response.immunisation.ScheduleItem
import com.google.gson.Gson
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.rv_child_item_immunisation.view.*
import kotlinx.android.synthetic.main.rv_item_immunisation.view.*
import javax.inject.Inject

class ImmunisationAdapter @Inject constructor() : AbstractExpandableItemAdapter<ImmunisationAdapter.ImmunisationHeaderViewHolder, ImmunisationAdapter.ImmunisationItemViewHolder>() {

  init {
    setHasStableIds(true)
  }

  override fun getChildCount(groupPosition: Int): Int {
    return mData?.get(groupPosition)?.schedule?.size ?: 0
  }

  override fun onCheckCanExpandOrCollapseGroup(holder: ImmunisationHeaderViewHolder?,
      groupPosition: Int, x: Int, y: Int, expand: Boolean): Boolean {
    return false
  }

  override fun onCreateGroupViewHolder(parent: ViewGroup,
      viewType: Int): ImmunisationHeaderViewHolder {

    return ImmunisationHeaderViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.rv_item_immunisation,
            parent, false))
  }

  override fun onCreateChildViewHolder(parent: ViewGroup,
      viewType: Int): ImmunisationItemViewHolder {
    return ImmunisationItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.rv_child_item_immunisation,
            parent, false))
  }

  override fun getGroupId(groupPosition: Int): Long {
    return groupPosition.toLong()
  }

  override fun onBindChildViewHolder(holder: ImmunisationItemViewHolder, groupPosition: Int,
      childPosition: Int, viewType: Int) {
    holder.bindTo(mData?.get(groupPosition)?.schedule?.get(childPosition)!!)
  }

  override fun getChildId(groupPosition: Int, childPosition: Int): Long {
    return childPosition.toLong()
  }

  override fun getGroupCount(): Int {
    return mData?.size ?: 0
  }

  override fun onBindGroupViewHolder(holder: ImmunisationHeaderViewHolder, groupPosition: Int,
      viewType: Int) {
    holder.bindTo(mData?.get(groupPosition))
  }

  private var mData: List<ImmunisationData>? = null


  fun setData(mData: ArrayList<ImmunisationData>?) {
    Logger.d("Data in Immunisation Adapter : " + Gson().toJson(mData))
    this.mData = mData
    notifyDataSetChanged()
  }

  inner class ImmunisationHeaderViewHolder(itemView: View) : AbstractExpandableItemViewHolder(
      itemView) {
    private val tvAge = itemView.age

    init {

    }

    fun bindTo(immunisationData: ImmunisationData?) {
      tvAge.setText("Age To Immunize : ".plus(immunisationData?.age))
    }
  }

  class ImmunisationItemViewHolder(itemView: View) : AbstractExpandableItemViewHolder(itemView) {

    private val tvRoute = itemView.route
    private val tvImmunisation = itemView.immunisation

    fun bindTo(scheduleItem: ScheduleItem) {
      tvRoute.setText(scheduleItem.route)

      val sb = StringBuilder()


      for (imm in scheduleItem.immunisation) {
        sb.append(imm).append("\n")
      }


      tvImmunisation.setText(sb)
//      itemView.age.setText(scheduleItem)
    }

  }
}