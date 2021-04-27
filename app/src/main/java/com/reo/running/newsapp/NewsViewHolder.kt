package com.reo.running.newsapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemImageView: ImageView = itemView.findViewById(R.id.imageNews)
    val itemContent: TextView = itemView.findViewById(R.id.contentNews)
    val itemPublish: TextView = itemView.findViewById(R.id.publishNews)
}