package com.dentist.pedodontics

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.dentist.pedodontics.features.authentication.AuthenticationActivity
import com.dentist.pedodontics.features.drawer.DrawerActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {


  @Inject
  lateinit var firebaseAuth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    AndroidInjection.inject(this)
    Logger.d("Bunty FirebaseAuth : $firebaseAuth")
  }

  override fun onStart() {
    super.onStart()
    Logger.d("Firebase Current User ${firebaseAuth.currentUser}")

  }


  override fun onPostResume() {
    super.onPostResume()
    Handler().postDelayed(Runnable {
      routeUser(firebaseAuth.currentUser)
    }, 2000)
  }


  /*
   * Handles the user routing
   */
  private fun routeUser(currentUser: FirebaseUser?) {
    if (currentUser != null) {
      startActivity(DrawerActivity.newInstance(this))
    } else {
      startActivity(AuthenticationActivity.newInstance(this));
    }
  }

}
