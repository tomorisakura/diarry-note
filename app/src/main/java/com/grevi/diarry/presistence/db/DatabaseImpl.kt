package com.grevi.diarry.presistence.db

import com.grevi.diarry.presistence.entity.DiaryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import javax.inject.Inject

class DatabaseImpl @Inject constructor(private val dao: DiaryDAO) : Database {
    override suspend fun insertDiary(diaryEntity: DiaryEntity) = dao.insertDiary(diaryEntity)
    override suspend fun getDiary(): Flow<List<DiaryEntity>> = flow { emit(dao.getDiary()) }
    override suspend fun updateDiary(title: String, content: String, updatedAt : String, id: String) = dao.updateDiary(title, content, updatedAt, id)
    override suspend fun deleteDiary(id: String) = dao.deleteDiary(id)
}