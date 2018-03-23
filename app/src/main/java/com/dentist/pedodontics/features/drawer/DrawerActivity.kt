package com.dentist.pedodontics.features.drawer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dentist.pedodontics.R
import com.dentist.pedodontics.features.base.BaseActivity
import com.dentist.pedodontics.features.developmental.MilestonesFragment
import com.dentist.pedodontics.features.physiological.IdealHeightFragment
import com.google.firebase.auth.FirebaseAuth
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.app_bar_drawer.*
import kotlinx.android.synthetic.main.nav_header_drawer.view.*
import javax.inject.Inject

class DrawerActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

  @Inject
  lateinit var mFirebaseAuth: FirebaseAuth


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_drawer)
    setSupportActionBar(toolbar)
    AndroidInjection.inject(this)

    /*fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }*/

    val toggle = ActionBarDrawerToggle(
        this, drawer_layout, toolbar, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close)
    drawer_layout.addDrawerListener(toggle)
    toggle.syncState()

    nav_view.setNavigationItemSelectedListener(this)
    val user = mFirebaseAuth.currentUser
    if (user != null) {
      for (i in user.providerData) {
        val photoURI = i.photoUrl
        Logger.d("Photo URL : ${photoURI}")
        Glide.with(this).load(photoURI).apply(RequestOptions.circleCropTransform()).into(
            nav_view.getHeaderView(0).profile);
      }
    }
  }

  override fun onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
      drawer_layout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
//    menuInflater.inflate(R.menu.drawer, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    when (item.itemId) {
      R.id.action_settings -> return true
      else -> return super.onOptionsItemSelected(item)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    when (item.itemId) {
      R.id.nav_immunisation -> {
        // Handle the camera action
      }
      R.id.nav_physiological -> {
        replaceFragment(IdealHeightFragment.newInstance())
      }
      R.id.nav_developmental -> {
        replaceFragment(MilestonesFragment.newInstance())
      }
      R.id.nav_oral -> {

      }
      R.id.nav_dentition -> {

      }
      R.id.nav_anxiety -> {

      }
      R.id.nav_behaviour -> {

      }
      R.id.nav_drug -> {

      }
      else -> {

      }
    }

    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }

  override fun onPostResume() {
    super.onPostResume()

  }

  companion object {
    fun newInstance(context: Context): Intent {
      return Intent(context, DrawerActivity::class.java)
    }
  }
}
