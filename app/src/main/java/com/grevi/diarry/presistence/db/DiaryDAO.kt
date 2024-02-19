package com.grevi.diarry.presistence.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grevi.diarry.presistence.entity.DiaryEntity
import java.time.LocalDateTime

@Dao
interface DiaryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiary(diaryEntity: DiaryEntity)

    @Query("SELECT * FROM diary")
    fun getDiary() : List<DiaryEntity>

    @Query("UPDATE diary SET title =:title, content=:content, updatedAt =:updatedAt WHERE id =:id")
    fun updateDiary(title : String, content : String, updatedAt : String, id : String)

    @Query("DELETE FROM diary WHERE id=:id")
    fun deleteDiary(id : String)

}