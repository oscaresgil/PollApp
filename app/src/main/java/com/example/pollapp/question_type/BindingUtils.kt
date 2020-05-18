package com.example.pollapp.question_type

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.pollapp.R

@BindingAdapter("typeImage")
fun ImageView.setTypeImage(index: Int?) {
    Log.i("Binding", index.toString())
    index?.let {
        setImageResource(when (index) {
            1 -> R.drawable.ic_comment
            2 -> R.drawable.ic_opinion
            3 -> R.drawable.ic_evaluation
            else -> R.drawable.trophy
        })
    }
}