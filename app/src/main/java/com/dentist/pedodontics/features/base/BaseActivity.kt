package com.dentist.pedodontics.features.base

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.dentist.pedodontics.R

/**
 * Created by bunty on 18/03/18.
 */
abstract class BaseActivity : AppCompatActivity() {


  private fun showSnakeBar(message: String) {
    val snack = Snackbar.make(findViewById(android.R.id.content),
        message, Snackbar.LENGTH_LONG)
    snack.show()
  }


  fun replaceFragment(
      fragment: BaseFragment, pairs: MutableList<Pair<View, String>>? = null) {
    val backStateName = fragment::class.java.name

    val manager = supportFragmentManager
    val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)

    if (!fragmentPopped && manager.findFragmentByTag(
            backStateName) == null) { //fragment not in back stack, create it.
      val ft = manager.beginTransaction()
      if (pairs != null) {
        for (pair in pairs) {
          ft.addSharedElement(pair.first, pair.second)
        }
      }
      /*ft.setCustomAnimations(R.anim.slide_in_from_right,
          R.anim.slide_out_to_left,
          R.anim.slide_in_from_left,
          R.anim.slide_out_to_right)*/

      ft.replace(R.id.container, fragment, backStateName)
      ft.addToBackStack(backStateName)
      ft.commit()
    }
  }


}