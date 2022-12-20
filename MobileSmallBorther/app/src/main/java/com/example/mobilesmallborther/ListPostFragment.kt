package com.example.mobilesmallborther

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesmallbrother.dtos.DtoInputPost

class ListPostFragment : Fragment() {
    private val postList: ArrayList<DtoInputPost> = arrayListOf()
    private val postAdapter = PostRecyclerViewAdapter(postList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = postAdapter
            }
        }
        return view
    }

    fun replacePostList(list: List<DtoInputPost>) {
        postList.clear()
        postList.addAll(list)
        postAdapter.notifyDataSetChanged()
    }

    fun addPost(dto: DtoInputPost) {
        postList.add(dto)
        postAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = ListPostFragment()
    }
}