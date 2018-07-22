package com.dentist.pedodontics.features.behaviour


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dentist.pedodontics.R
import com.dentist.pedodontics.data.models.response.behaviour.DataItem
import com.dentist.pedodontics.features.base.BaseFragment
import com.google.firebase.database.*
import com.orhanobut.logger.Logger
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_behaviour.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class BehaviourFragment : BaseFragment() {

  @Inject
  lateinit var mDatabaseReference: DatabaseReference

  @Inject
  lateinit var mAdapter: BehaviourAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidSupportInjection.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_behaviour, container, false)
  }

  override fun onStart() {
    super.onStart()

    mDatabaseReference = mDatabaseReference.child(
        "behaviour")

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


          /*Logger.d("Printing Children : ${p0.childrenCount}")

          if (p0.hasChildren()) {
            Logger.d("Printing Children : ${p0.children}")
          }

          var mList: MutableList<DataItem> = arrayListOf()
          if (p0 != null) {
            for (data in p0.children) {
              mList.add(Gson().fromJson(Gson().toJson(data.value),
                  DataItem::class.java))
            }

            for (element in mList) {
              Logger.d("ValueEventListener List : " + element)
            }

            mAdapter.setData(mList as ArrayList<DataItem>?)
          } else {
            Logger.d("ValueEventListener onDataChange is NULL")
          }*/
        }
      }
    })


    /*mDatabaseReference.addChildEventListener(object : ChildEventListener {
      override fun onCancelled(p0: DatabaseError) {
        Logger.d("Printing onCancelled : ${p0.message}")
      }

      override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        Logger.d("Printing onChildMoved : ${p0.childrenCount}")
      }

      override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        Logger.d("Printing onCancelled : ${p0.childrenCount}")
      }

      override fun onChildAdded(p0: DataSnapshot, p1: String?) {
        Logger.d("Printing onChildChanged : ${p0.childrenCount}")
      }

      override fun onChildRemoved(p0: DataSnapshot) {
        Logger.d("Printing onChildRemoved : ${p0.childrenCount}")
      }

    })*/
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rvBehaviour.isNestedScrollingEnabled = false
    rvBehaviour.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    rvBehaviour.adapter = mAdapter

    Logger.d("Firebase Database Reference $mDatabaseReference")
  }

  companion object {
    fun newInstance(): BehaviourFragment {
      val fragment = BehaviourFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }


}
