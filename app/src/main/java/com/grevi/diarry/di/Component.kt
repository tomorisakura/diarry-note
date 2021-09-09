package com.grevi.diarry.di

import android.content.Context
import com.grevi.diarry.presistence.db.Database
import com.grevi.diarry.presistence.db.DatabaseImpl
import com.grevi.diarry.repository.Repository
import com.grevi.diarry.repository.RepositoryImpl
import com.grevi.diarry.utils.SharedUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Component {

    @Provides
    @Singleton
    fun provideDatabase(databaseImpl: DatabaseImpl): Database = databaseImpl

    @Provides
    @Singleton
    fun provideRepository(repositoryImpl: RepositoryImpl) : Repository = repositoryImpl

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedUtils {
        return SharedUtils(context)
    }
}