package com.dentist.pedodontics.di.component

import com.dentist.pedodontics.App
import com.dentist.pedodontics.SplashActivity
import com.dentist.pedodontics.di.module.AppModule
import com.dentist.pedodontics.di.module.BuilderModule
import com.dentist.pedodontics.features.behaviour.BehaviourFragment
import com.dentist.pedodontics.features.immunisation.ImmunisationFragment
import com.dentist.pedodontics.features.oral.OralHygieneFragment
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by bunty on 12/03/18.
 */

@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class, BuilderModule::class])
public interface AppComponent {

  fun inject(app: App)

  fun inject(splashActivity: SplashActivity)

  fun inject(immunisationFragment: ImmunisationFragment)

  fun inject(oralHygieneFragment: OralHygieneFragment)

  fun inject(behaviourFragment: BehaviourFragment)

}