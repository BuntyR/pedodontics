package com.dentist.pedodontics.features.oral


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dentist.pedodontics.R
import com.dentist.pedodontics.data.models.response.oral.DataItem
import com.dentist.pedodontics.features.base.BaseFragment
import com.google.firebase.database.*
import com.orhanobut.logger.Logger
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_oral_hygiene.*
import javax.inject.Inject


class OralHygieneFragment : BaseFragment() {

  @Inject
  lateinit var mFirebaseDatabase: DatabaseReference

  @Inject
  lateinit var mAdapter: OralHygieneAdapter

  private var mLayoutManager: RecyclerView.LayoutManager? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidSupportInjection.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_oral_hygiene, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mLayoutManager = LinearLayoutManager(context)
    rvHygiene.isNestedScrollingEnabled = false
    rvHygiene.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    rvHygiene.adapter = mAdapter

    mFirebaseDatabase.child("hygiene").addValueEventListener(object : ValueEventListener {
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
                data.getValue(object : GenericTypeIndicator<ArrayList<DataItem>>() {}))
        } else {
          Logger.d("ValueEventListener onDataChange is NULL")
        }


        /*if (p0 != null) {
          Logger.d("ValueEventListener onDataChange is NOT  NULL $p0")
          for (data in p0.children)
            if (data.hasChild("data")) {
              val oralData: ArrayList<DataItem> = data.child("data").getValue() as ArrayList<DataItem>
              mAdapter.setData(oralData)
            }
        } else {
          Logger.d("ValueEventListener onDataChange is NULL")
        }*/
      }
    })

  }

  companion object {
    fun newInstance(): OralHygieneFragment {
      val fragment = OralHygieneFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }


}
