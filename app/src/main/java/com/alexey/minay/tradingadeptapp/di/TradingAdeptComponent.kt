package com.alexey.minay.tradingadeptapp.di

import com.alexey.minay.core_dagger2.NeedInitializeException
import com.alexey.minay.core_dagger2.ViewModelProviderFactory
import com.alexey.minay.feature_navigation_impl.di.NavigationComponent
import com.alexey.minay.feature_navigation_impl.di.NavigationDependencies
import com.alexey.minay.feature_onboarding_impl.OnBoardingFragmentProvider
import com.alexey.minay.feature_onboarding_impl.di.OnBoardingComponent
import com.alexey.minay.feature_onboarding_impl.di.OnBoardingDependencies
import com.alexey.minay.feature_quotes_chart_impl.navigation.QuotesFragmentsProvider
import dagger.Component

@Component(
    modules = [TradingAdeptViewModelBinding::class],
    dependencies = [TradingAdeptDependencies::class]
)

interface TradingAdeptComponent {

    val viewModelProviderFactory: ViewModelProviderFactory

    companion object {

        private var mComponent: TradingAdeptComponent? = null

        fun get() = mComponent ?: throw NeedInitializeException()

        fun init() {
            NavigationComponent.init(
                dependencies = object : NavigationDependencies {
                    override fun provideQuotesFragmentProvider() =
                        QuotesFragmentsProvider()

                    override fun provideOnBoardingFragmentProvider() =
                        OnBoardingFragmentProvider()
                }
            )

            OnBoardingComponent.init(
                dependenciesProvider = object : OnBoardingDependencies {
                    override fun provideNavigator() =
                        NavigationComponent.get().screenNavigator
                }
            )

            val dependencies = object : TradingAdeptDependencies {
                override fun provideNavigationScreen() = NavigationComponent.get().screenNavigator
            }

            mComponent = DaggerTradingAdeptComponent.builder()
                .tradingAdeptDependencies(dependencies)
                .build()
        }

    }

}