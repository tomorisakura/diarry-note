package com.ekik.core_common.repository

import com.ekik.core_common.presistence.db.DatabaseImpl
import com.ekik.core_common.presistence.entity.DiaryEntity
import com.ekik.core_common.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val databaseImpl: DatabaseImpl) : Repository {

    override suspend fun insertDiary(diaryEntity: DiaryEntity) = databaseImpl.insertDiary(diaryEntity)
    override suspend fun getDiary(): Flow<List<DiaryEntity>> = databaseImpl.getDiary()
    override suspend fun updateDiary(title: String, content: String, updatedAt : String, id: String) = databaseImpl.updateDiary(title, content, updatedAt, id)
    override suspend fun deleteDiary(id: String) = databaseImpl.deleteDiary(id)
}