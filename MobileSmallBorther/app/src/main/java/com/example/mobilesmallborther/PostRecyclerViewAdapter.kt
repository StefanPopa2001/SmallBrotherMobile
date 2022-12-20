package com.example.mobilesmallborther

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        holder.idView.text = item.idPost.toString()
        holder.titleView.text = item.descriptionPost
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val titleView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }

}