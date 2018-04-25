package com.dentist.pedodontics.features.immunisation


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dentist.pedodontics.R
import com.dentist.pedodontics.features.base.BaseFragment
import com.google.firebase.database.*
import com.orhanobut.logger.Logger
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_immunisation.*
import javax.inject.Inject


class ImmunisationFragment : BaseFragment() {

  @Inject
  lateinit var mFirebaseDatabase: DatabaseReference

  @Inject
  lateinit var mImmunisationAdapter: ImmunisationAdapter

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
    Logger.d("onViewCreated")
    rvImmunisation.isNestedScrollingEnabled = false
    rvImmunisation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    rvImmunisation.adapter = mImmunisationAdapter
    mFirebaseDatabase.child("immunisation").addValueEventListener(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError?) {
        if (p0 != null) {
          Logger.d("ValueEventListener The read failed: ${p0.message}")
        }else{
          Logger.d("ValueEventListener onCancelled is NULL")
        }
      }

      override fun onDataChange(p0: DataSnapshot?) {
        if (p0 != null) {
          for (data in p0.children)
            Logger.d("ValueEventListener DataSnapshot $data")
        }else{
          Logger.d("ValueEventListener onDataChange is NULL")
        }
      }
    })

    mFirebaseDatabase.child("immunisation").addChildEventListener(object : ChildEventListener {
      override fun onCancelled(p0: DatabaseError?) {
        Logger.d("onCancelled $p0")
      }

      override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
        Logger.d("onChildMoved $p0")
      }

      override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
        Logger.d("onChildChanged $p0")
      }

      override fun onChildRemoved(p0: DataSnapshot?) {
        Logger.d("onChildRemoved $p0")
      }

      override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
        Logger.d("onChildAdded : $p0")
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
