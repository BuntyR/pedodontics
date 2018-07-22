package com.dentist.pedodontics.features.immunisation


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dentist.pedodontics.R
import com.dentist.pedodontics.data.models.response.immunisation.ImmunisationData
import com.dentist.pedodontics.features.base.BaseFragment
import com.google.firebase.database.*
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager
import com.orhanobut.logger.Logger
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_immunisation.*
import javax.inject.Inject


class ImmunisationFragment : BaseFragment() {

  @Inject
  lateinit var mFirebaseDatabase: DatabaseReference

  @Inject
  lateinit var mAdapter: ImmunisationAdapter

  lateinit var immunisationDataList: List<ImmunisationData>


  private var mRecyclerView: RecyclerView? = null
  private var mLayoutManager: RecyclerView.LayoutManager? = null
  //  private var mAdapter: ImmunisationAdapter? = null
  private var mWrappedAdapter: RecyclerView.Adapter<*>? = null
  private var mRecyclerViewExpandableItemManager: RecyclerViewExpandableItemManager? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidSupportInjection.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_immunisation, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    mLayoutManager = LinearLayoutManager(context)
    mRecyclerViewExpandableItemManager = RecyclerViewExpandableItemManager(null)
    mRecyclerViewExpandableItemManager!!.setDefaultGroupsExpandedState(true)
    val mWrappedAdapter = mRecyclerViewExpandableItemManager!!.createWrappedAdapter(
        mAdapter
    )// wrap for expanding

    rvImmunisation.isNestedScrollingEnabled = false
    rvImmunisation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    rvImmunisation.adapter = mWrappedAdapter
    mRecyclerViewExpandableItemManager!!.attachRecyclerView(rvImmunisation)
    mFirebaseDatabase.child("immunisation").addValueEventListener(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError) {
        if (p0 != null) {
          Logger.d("ValueEventListener The read failed: ${p0.message}")
        } else {
          Logger.d("ValueEventListener onCancelled is NULL")
        }
      }

      override fun onDataChange(p0: DataSnapshot) {
        if (p0 != null) {
          for (data in p0.children)
            mAdapter.setData(
                data.getValue(object : GenericTypeIndicator<ArrayList<ImmunisationData>>() {}))
        } else {
          Logger.d("ValueEventListener onDataChange is NULL")
        }
      }
    })

  }

  companion object {
    fun newInstance(): ImmunisationFragment {
      val fragment = ImmunisationFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }

}
