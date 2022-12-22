package com.example.mobilesmallborther

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobilesmallborther.databinding.FragmentProfilItemBinding
import com.example.mobilesmallbrother.dtos.DtoInputAnimal


class ProfileRecyclerViewAdapter(
private val values: List<DtoInputAnimal>
) : RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProfilItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        var url = item.urlImage
        Glide.with(holder.imageView)
            .load(url)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProfilItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.ivfragmentMyAnimalsCard


        override fun toString(): String {
            return super.toString() + " '" + "'"
        }
    }
}