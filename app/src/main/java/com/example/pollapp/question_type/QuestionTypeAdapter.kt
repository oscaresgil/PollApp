package com.example.pollapp.question_type

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pollapp.database.QuestionType
import com.example.pollapp.databinding.QuestionTypeItemLayoutBinding

class QuestionTypeAdapter (val listener : QuestionTypeClickListener) : ListAdapter<QuestionType, QuestionTypeAdapter.ViewHolder>(QuestionTypeDiffCallback()) {

    override fun onBindViewHolder(holder: QuestionTypeAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(listener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionTypeAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: QuestionTypeItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: QuestionTypeClickListener, item: QuestionType){
            binding.questionType = item
            binding.clickListener = clickListener
            Log.i("QuestionTypeAdapter", item.toString())
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = QuestionTypeItemLayoutBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }
}

class QuestionTypeDiffCallback : DiffUtil.ItemCallback<QuestionType>() {

    override fun areItemsTheSame(oldItem: QuestionType, newItem: QuestionType): Boolean {
        return oldItem.typeId == newItem.typeId
    }
    override fun areContentsTheSame(oldItem: QuestionType, newItem: QuestionType): Boolean {
        return oldItem == newItem
    }

}

class QuestionTypeClickListener (val clickListener: (typeId: Long) -> Unit) {
    fun onClick(type: QuestionType) = clickListener(type.typeId)
}
