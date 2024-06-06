package com.ekik.core_common.presistence.db

import com.ekik.core_common.presistence.entity.DiaryEntity
import kotlinx.coroutines.flow.Flow

interface Database {
    suspend fun insertDiary(diaryEntity: DiaryEntity)
    suspend fun getDiary() : Flow<List<DiaryEntity>>
    suspend fun updateDiary(title : String, content : String, updatedAt : String, id : String)
    suspend fun deleteDiary(id : String)
}