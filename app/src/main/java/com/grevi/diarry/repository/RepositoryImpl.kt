package com.grevi.diarry.repository

import com.grevi.diarry.presistence.db.DatabaseImpl
import com.grevi.diarry.presistence.entity.DiaryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val databaseImpl: DatabaseImpl) : Repository {

    override suspend fun insertDiary(diaryEntity: DiaryEntity) = databaseImpl.insertDiary(diaryEntity)
    override suspend fun getDiary(): Flow<List<DiaryEntity>> = databaseImpl.getDiary()
    override suspend fun updateDiary(title: String, content: String, updatedAt : String, id: String) = databaseImpl.updateDiary(title, content, updatedAt, id)
    override suspend fun deleteDiary(id: String) = databaseImpl.deleteDiary(id)
}