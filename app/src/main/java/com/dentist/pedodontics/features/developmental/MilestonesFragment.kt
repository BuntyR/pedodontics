package com.dentist.pedodontics.features.developmental

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dentist.pedodontics.R
import com.dentist.pedodontics.features.base.BaseFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.orhanobut.logger.Logger
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MilestonesFragment : BaseFragment() {

  @Inject
  lateinit var mDatabaseReference: DatabaseReference

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidSupportInjection.inject(this)

  }

  override fun onStart() {
    super.onStart()
    mDatabaseReference.child("pedodontics").child(
        "developmental").addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError?) {
        Logger.d("Databse Error : ${p0.toString()}")
      }

      override fun onDataChange(p0: DataSnapshot?) {
        Logger.d("Databse Result : ${p0.toString()}")
      }

    })
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_milestones, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Logger.d("Firebase Database Reference $mDatabaseReference")
  }

  companion object {
    fun newInstance(): MilestonesFragment {
      val fragment = MilestonesFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }

}// Required empty public constructor
