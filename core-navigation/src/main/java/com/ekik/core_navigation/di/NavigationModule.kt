package com.ekik.core_navigation.di

import android.content.Context
import com.ekik.core_navigation.navigation.IntroNavigationImpl
import com.ekik.core_navigation.navigation.SplashNavigationImpl
import com.ekik.feature_intro.ui.navigation.IntroNavigation
import com.ekik.feature_splash.navigation.SplashNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class NavigationModule {

    @Provides
    fun provideSplashNavigation(@ApplicationContext context: Context): SplashNavigation {
        return SplashNavigationImpl(context)
    }

    @Provides
    fun provideIntroNavigation(@ApplicationContext context: Context): IntroNavigation {
        return IntroNavigationImpl(context)
    }
}