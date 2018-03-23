package com.dentist.pedodontics

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.dentist.pedodontics.di.component.AppComponent
import com.dentist.pedodontics.di.component.DaggerAppComponent
import com.dentist.pedodontics.di.module.AppModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by bunty on 12/03/18.
 */
class App : Application(), HasActivityInjector, HasSupportFragmentInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  @Inject
  lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun onCreate() {
    super.onCreate()

    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }
    LeakCanary.install(this)

    if (BuildConfig.DEBUG) {
      setUpLogs()
    }


    appComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule())
        .build()

    appComponent.inject(this)


  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return dispatchingAndroidInjector
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return dispatchingFragmentInjector
  }

  private fun setUpLogs() {
    val formatStrategy = PrettyFormatStrategy.newBuilder()
        .methodCount(6)         // (Optional) How many method line to show. Default 2
        .methodOffset(
            0)        // (Optional) Hides internal method calls up to offset. Default 5
        .build()

    Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
  }

  companion object {
    lateinit var appComponent: AppComponent
  }

}