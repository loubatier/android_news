package com.example.news.adapters;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.models.Category

class CategoryAdapter (
    private val dataset: List<Category>,
    private val callback: (category: Category) -> Unit)
    :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Category) {
            root.setOnClickListener {
                callback(item)
            }
            val catTitle = root.findViewById<TextView>(R.id.category_title)
            val catDesc = root.findViewById<TextView>(R.id.category_description)
            val catImg = root.findViewById<ImageView>(R.id.category_image)

            catTitle.text = item.name
            catDesc.text = item.description

            Glide.with(root).load(item.urlToImage).into(catImg);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_category_item, parent, false)
        return ViewHolder(rootView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() : Int = dataset.size

}
