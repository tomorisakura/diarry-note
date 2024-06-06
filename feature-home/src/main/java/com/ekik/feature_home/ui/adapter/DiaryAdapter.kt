package com.ekik.feature_home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekik.core_common.presistence.entity.DiaryEntity
import com.ekik.feature_home.databinding.ListDiaryBinding

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.DiaryVH>() {

    private val diaryList : ArrayList<DiaryEntity> = ArrayList()
    var onItemClicked : ((DiaryEntity) -> Unit)? = null

    inner class DiaryVH(private val binding : ListDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diary : DiaryEntity) {
            with(binding) {
                diaryTitle.text = diary.title
                diaryContent.text = diary.content
            }
        }
    }

    internal fun addItem(item : List<DiaryEntity>) {
        diaryList.clear()
        diaryList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryVH {
        val binding = ListDiaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiaryVH(binding)
    }

    override fun onBindViewHolder(holder: DiaryVH, position: Int) {
        holder.bind(diaryList[position])
        holder.itemView.setOnClickListener { onItemClicked?.invoke(diaryList[position]) }
    }

    override fun getItemCount(): Int = diaryList.size
}