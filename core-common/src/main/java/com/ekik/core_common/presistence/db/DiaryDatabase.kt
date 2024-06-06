package com.ekik.core_common.presistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ekik.core_common.presistence.entity.DiaryEntity

@Database(entities = [DiaryEntity::class], version = 1, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun diaryDAO() : DiaryDAO
}