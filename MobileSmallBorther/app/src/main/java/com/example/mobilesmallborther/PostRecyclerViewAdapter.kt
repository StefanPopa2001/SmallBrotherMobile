package com.example.mobilesmallborther

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobilesmallborther.databinding.FragmentPostItemBinding
import com.example.mobilesmallbrother.dtos.DtoInputPost

class PostRecyclerViewAdapter(
    private val values: List<DtoInputPost>
) : RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentPostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.datePost
        holder.titleView.text = item.townDisparition
        var url = item.urlImage
        Glide.with(holder.imageView)
            .load(url)
            .into(holder.imageView)
        Log.i("Valeur", item.descriptionPost)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemPicture
        val titleView: TextView = binding.content
        val imageView: ImageView = binding.imageView

        init {
            itemView.setOnClickListener {
                val builder = AlertDialog.Builder(itemView.context)
                val item = values[bindingAdapterPosition]
                builder.setTitle("Information de la disparition")
                builder.setMessage(item.descriptionPost)
                builder.setPositiveButton("OK") { _, _ ->
                }
                val dialog = builder.create()
                dialog.show()
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }
}