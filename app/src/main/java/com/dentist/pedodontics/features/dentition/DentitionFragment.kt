package com.dentist.pedodontics.features.dentition


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dentist.pedodontics.R
import com.dentist.pedodontics.data.models.response.dentition.DataItem
import com.dentist.pedodontics.features.base.BaseFragment
import com.google.firebase.database.*
import com.orhanobut.logger.Logger
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_dentition.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class DentitionFragment : BaseFragment() {

  @Inject
  lateinit var mDatabaseReference: DatabaseReference

  @Inject
  lateinit var mAdapter: DentitionAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidSupportInjection.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_dentition, container, false)
  }


  override fun onStart() {
    super.onStart()

    mDatabaseReference = mDatabaseReference.child(
        "dentition")

    mDatabaseReference.keepSynced(true)

    mDatabaseReference.addValueEventListener(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError) {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
      }

      override fun onDataChange(p0: DataSnapshot) {


        if (p0.exists()) {
          if (p0 != null) {
            for (data in p0.children)
              mAdapter.setData(
                  data.getValue(object : GenericTypeIndicator<ArrayList<DataItem>>() {}))
          } else {
            Logger.d("ValueEventListener onDataChange is NULL")
          }
        }
      }
    })

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rvDentition.isNestedScrollingEnabled = false
    rvDentition.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    rvDentition.adapter = mAdapter

    Logger.d("Firebase Database Reference $mDatabaseReference")
  }

  companion object {
    fun newInstance(): DentitionFragment {
      val fragment = DentitionFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }


}
