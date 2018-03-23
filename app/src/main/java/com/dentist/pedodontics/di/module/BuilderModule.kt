package com.dentist.pedodontics.di.module

import com.dentist.pedodontics.SplashActivity
import com.dentist.pedodontics.features.authentication.AuthenticationActivity
import com.dentist.pedodontics.features.developmental.MilestonesFragment
import com.dentist.pedodontics.features.drawer.DrawerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by bunty on 18/03/18.
 */

@Module
abstract class BuilderModule {

  @ContributesAndroidInjector
  internal abstract fun splashActivity(): SplashActivity

  @ContributesAndroidInjector
  internal abstract fun authenticationActivity(): AuthenticationActivity

  @ContributesAndroidInjector
  internal abstract fun drawerActivity(): DrawerActivity

  @ContributesAndroidInjector
  internal abstract fun milestonesFragment(): MilestonesFragment
}