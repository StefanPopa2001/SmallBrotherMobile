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
    private lateinit var detailPostFragment: DetailPostFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = postAdapter.apply {
                    onItemOnClickListener = {
                        detailPostFragment = DetailPostFragment(it)
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView_fragmentPostManager, detailPostFragment)
                        .commit()
                    }
                }
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
        @JvmStatic
        fun newInstance() = ListPostFragment()
    }
}