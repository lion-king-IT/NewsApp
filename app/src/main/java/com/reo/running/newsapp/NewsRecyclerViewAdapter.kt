package com.reo.running.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class NewsRecyclerViewAdapter(private val context: Context, private val dataset: List<Article>) :
    RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val article = dataset[position]

        val options = RequestOptions().centerCrop()
        Glide.with(context).load(article.urlToImage).apply(options).into(holder.itemImageView)

//        holder.itemImageView.setImageURI(dataset[position].urlToImage.toUri())
//        holder.itemContent.text = dataset[position].content
//        holder.itemPublish.text = dataset[position].publishedAt
    }

    override fun getItemCount(): Int = dataset.size

}