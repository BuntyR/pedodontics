package com.dentist.pedodontics.di.module

import android.content.Context
import com.dentist.pedodontics.App
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.orhanobut.logger.Logger
import dagger.Module
import dagger.Provides

/**
 * Created by bunty on 12/03/18.
 */

@Module
class AppModule() {

  @Provides
  fun provideContext(application: App): Context {
    Logger.d("Bunty App : $application")
    return application.applicationContext
  }

  @Provides
  fun provideFirebaseInstance(): FirebaseAuth {
    val firedatbase = FirebaseAuth.getInstance()
    return firedatbase
  }

  @Provides
  fun provideFirebaseDatabase(): FirebaseDatabase {
    val firedatbase = FirebaseDatabase.getInstance()
//    firedatbase.setPersistenceEnabled(false)
//    firedatbase.setLogLevel(com.google.firebase.database.Logger.Level.DEBUG)
    return firedatbase
  }

  @Provides
  fun provideFirebaseDatabaseInstance(mFirebaseDatabase: FirebaseDatabase): DatabaseReference {
//    mFirebaseDatabase.setPersistenceEnabled(false)
//    mFirebaseDatabase.setLogLevel(com.google.firebase.database.Logger.Level.DEBUG)
    return mFirebaseDatabase.reference;
  }

}