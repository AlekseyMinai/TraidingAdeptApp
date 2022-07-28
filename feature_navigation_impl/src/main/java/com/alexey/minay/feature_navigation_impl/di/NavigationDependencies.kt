package com.alexey.minay.feature_navigation_impl.di

import com.alexey.minay.feature_onboarding_api.IOnBoardingFragmentProvider
import com.alexey.minay.feature_quotes_chart_api.IQuotesFragmentsProvider

interface NavigationDependencies {
    fun provideQuotesFragmentProvider(): IQuotesFragmentsProvider
    fun provideOnBoardingFragmentProvider(): IOnBoardingFragmentProvider
}