package com.token.dvco.token_kotlin

import android.support.v7.widget.RecyclerView
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RecyclerAdapter(games: List<MainActivity.Game>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val gamesStrings = games


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val parsedtxt = gamesStrings[position]

        holder.title.text = parsedtxt.name
        Picasso.with(holder.image.context).load(parsedtxt.image).resize(300,300).centerCrop().into(holder.image)
        holder.trailer.text = parsedtxt.trailer
        holder.trailer.movementMethod = LinkMovementMethod.getInstance()
        holder.release.text = parsedtxt.release

        val sb = StringBuilder("Disponivel em : ")
        val platforms = parsedtxt.platforms
            if (platforms != null) {
                for (i in platforms) {
                    sb.append(i).append(", ")
            }
        }
        sb.delete(sb.length-2, sb.length-1)
        holder.platforms.text = sb

    }

    override fun getItemCount(): Int {
        return gamesStrings.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title = view.findViewById(R.id.title) as TextView
        var image = view.findViewById(R.id.image) as ImageView
        var trailer = view.findViewById(R.id.trailer) as TextView
        var release = view.findViewById(R.id.release) as TextView
        var platforms = view.findViewById(R.id.platforms) as TextView

    }
}