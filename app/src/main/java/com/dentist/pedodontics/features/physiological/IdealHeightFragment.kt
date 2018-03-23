package com.dentist.pedodontics.features.physiological

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dentist.pedodontics.R
import com.dentist.pedodontics.features.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class IdealHeightFragment : BaseFragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_ideal_height, container, false)
  }


  companion object {
    fun newInstance(): IdealHeightFragment {
      val fragment = IdealHeightFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }


}// Required empty public constructor
