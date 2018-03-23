package com.dentist.pedodontics.features.base

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment


/**
 * Created by bunty on 18/03/18.
 */
abstract class BaseFragment : Fragment() {


  private fun showSnakeBar(@StringRes message: Int) {
    if (view != null) {
      val snack = Snackbar.make(view!!,
          message, Snackbar.LENGTH_LONG)
      snack.show()
    }
  }

  private fun showSnakeBar(message: String) {
    if (view != null) {
      val snack = Snackbar.make(view!!,
          message, Snackbar.LENGTH_LONG)
      snack.show()
    }
  }

}