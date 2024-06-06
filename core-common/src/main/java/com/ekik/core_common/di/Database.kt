package com.ekik.core_common.di

import android.content.Context
import androidx.room.Room
import com.ekik.core_common.presistence.db.DiaryDAO
import com.ekik.core_common.presistence.db.DiaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Database {

    private const val DB_NAME = "diaryDB"

    @Provides
    @Singleton
    fun provideDiaryDatabase(@ApplicationContext context : Context) : DiaryDatabase {
        return Room.databaseBuilder(context, DiaryDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDiaryDAO(database: DiaryDatabase) : DiaryDAO = database.diaryDAO()

}