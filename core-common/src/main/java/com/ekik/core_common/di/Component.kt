package com.ekik.core_common.di

import android.content.Context
import com.ekik.core_common.presistence.db.Database
import com.ekik.core_common.presistence.db.DatabaseImpl
import com.ekik.core_common.repository.Repository
import com.ekik.core_common.repository.RepositoryImpl
import com.ekik.core_common.utils.SharedUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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