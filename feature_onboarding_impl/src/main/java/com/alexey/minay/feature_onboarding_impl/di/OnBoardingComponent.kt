package com.alexey.minay.feature_onboarding_impl.di

import com.alexey.minay.core_dagger2.NeedInitializeException
import com.alexey.minay.core_dagger2.ViewModelProviderFactory
import dagger.Component

@Component(
    modules = [OnBoardingBindings::class],
    dependencies = [OnBoardingDependencies::class]
)
interface OnBoardingComponent {

    val viewModelProviderFactory: ViewModelProviderFactory

    companion object {

        private var mDependencies: OnBoardingDependencies? = null

        fun init(dependencies: OnBoardingDependencies) {
            this.mDependencies = dependencies
        }

        internal fun create() = DaggerOnBoardingComponent
            .builder()
            .onBoardingDependencies(
                mDependencies ?: throw NeedInitializeException()
            ).build()

    }

}