package com.grevi.diarry.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grevi.diarry.presistence.entity.DiaryEntity
import com.grevi.diarry.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _allDiary = MutableStateFlow<State<List<DiaryEntity>>>(State.Data)
    val allDiary : StateFlow<State<List<DiaryEntity>>> get() = _allDiary

    init {
        getAllDiary()
    }

    private fun getAllDiary() {
        viewModelScope.launch {
            repository.getDiary().collect { diary ->
                _allDiary.value = State.Loading()
                try {
                    _allDiary.value = State.Success(diary)
                }catch (e : Exception) {
                    _allDiary.value = State.Error(e)
                }
            }
        }
    }

    internal fun insertDiary(title : String, content : String) {
        viewModelScope.launch {
            val diary = DiaryEntity(title = title, content = content, updatedAt = null)
            repository.insertDiary(diary)
        }
    }

    internal fun updateDiary(title : String, content : String, id : String) {
        viewModelScope.launch {
            val updatedAt = LocalDateTime.now().toString()
            repository.updateDiary(title, content, updatedAt, id)
        }
    }

    internal fun deleteDiary(id : String) {
        viewModelScope.launch {
            repository.deleteDiary(id)
        }
    }

    //only restricted data
    sealed class State<out T> {
        data class Loading(var msg : String = "Memuat Diary") : State<Nothing>()
        data class Success<T>(val data : T) : State<T>()
        data class Error(val exception : Throwable) : State<Nothing>()
        object Data : State<Nothing>()
    }

}
