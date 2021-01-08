package org.shaun.itunessearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.shaun.itunessearch.R
import org.shaun.itunessearch.modelclass.ITunesItem

class GridAdapter(private var data: List<ITunesItem>? = emptyList<ITunesItem>()) : RecyclerView.Adapter<GridAdapter.ItemsViewHolder>() {


    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coverImage: ImageView = itemView.findViewById<ImageView>(R.id.artwork)
        val artistName: TextView = itemView.findViewById<TextView>(R.id.artist_name)
        val collection: TextView = itemView.findViewById<TextView>(R.id.collection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_cell, parent, false)
        return ItemsViewHolder(itemView)
    }

    override fun getItemCount() :Int
    {
        return if (data==null) {
            0
        }else{
            data!!.size
        }
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val current = data?.get(position)
        holder.artistName.text = current?.artistName
        holder.collection.text = current?.collectionName
        Picasso.get().load(current?.artworkUrl100).into(holder.coverImage)
    }

    internal fun updateData(newData: List<ITunesItem>?) {
        this.data=newData
        notifyDataSetChanged()
    }

}