package com.token.dvco.token_kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RecyclerAdapter(strings : List<MainActivity.Gaem>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val str = strings


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val text = str[position]

        holder.title.text = text.name
        Picasso.with(holder.image.context).load(text.image).resize(250,250).centerCrop().into(holder.image)
        holder.trailer.text = text.trailer
        holder.release.text = text.release
        holder.platforms.text = text.platforms.toString()


    }

    override fun getItemCount(): Int {
        return str.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title = view.findViewById(R.id.title) as TextView
        var image = view.findViewById(R.id.image) as ImageView
        var trailer = view.findViewById(R.id.trailer) as TextView
        var release = view.findViewById(R.id.release) as TextView
        var platforms = view.findViewById(R.id.platforms) as TextView

    }
}