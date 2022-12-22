package com.example.mobilesmallborther

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobilesmallborther.databinding.FragmentPostItemBinding
import com.example.mobilesmallbrother.dtos.DtoInputPost

class PostRecyclerViewAdapter(
    private val values: List<DtoInputPost>
) : RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {

    var onItemOnClickListener:((item: DtoInputPost)-> Unit)? = null;

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
        holder.itemView.setOnClickListener{
            val item = values[position]
            onItemOnClickListener?.invoke(item)
        }
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


            itemView.setOnLongClickListener {

                val item = values[bindingAdapterPosition]
                val place = item.townDisparition
                val googleMapsUrl = "google.navigation:q=$place"

                val uri = Uri.parse(googleMapsUrl)
                val googleMapsPackage = "com.google.android.apps.maps"
                val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                    setPackage(googleMapsPackage)
                }
                itemView.context.startActivity(intent)
                 true

            }







        }

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }
}