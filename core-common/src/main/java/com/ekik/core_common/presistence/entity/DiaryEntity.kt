package com.ekik.core_common.presistence.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "diary")
@Parcelize
data class DiaryEntity(
    @PrimaryKey @ColumnInfo(name = "id") var id : String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") var title : String,
    @ColumnInfo(name = "content") var content : String,
    @ColumnInfo(name = "createdAt") var cratedAt : String = LocalDateTime.now().toString(),
    @ColumnInfo(name = "updatedAt") var updatedAt : String?
) : Parcelable
