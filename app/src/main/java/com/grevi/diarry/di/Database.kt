package com.grevi.diarry.di

import android.content.Context
import androidx.room.Room
import com.grevi.diarry.presistence.db.Database
import com.grevi.diarry.presistence.db.DatabaseImpl
import com.grevi.diarry.presistence.db.DiaryDAO
import com.grevi.diarry.presistence.db.DiaryDatabase
import com.grevi.diarry.repository.Repository
import com.grevi.diarry.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Database {

    @Provides
    @Singleton
    fun provideDiaryDatabase(@ApplicationContext context : Context) : DiaryDatabase {
        return Room.databaseBuilder(context, DiaryDatabase::class.java, "diaryDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDiaryDAO(database: DiaryDatabase) : DiaryDAO = database.diaryDAO()

    @Provides
    @Singleton
    fun provideDatabase(databaseImpl: DatabaseImpl) : Database = databaseImpl

    @Provides
    @Singleton
    fun provideRepository(repositoryImpl: RepositoryImpl) : Repository = repositoryImpl

}