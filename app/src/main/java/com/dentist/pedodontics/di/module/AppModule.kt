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
    return FirebaseAuth.getInstance();
  }

  @Provides
  fun provideFirebaseDatabaseInstance(): DatabaseReference {
    return FirebaseDatabase.getInstance().reference;
  }

}