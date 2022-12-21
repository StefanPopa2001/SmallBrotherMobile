package com.example.mobilesmallborther

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesmallbrother.dtos.DtoInputPost

class ListProfilFragment : Fragment() {
    private val profilList: ArrayList<DtoInputPost> = arrayListOf()
    private val profilAdapter = ProfileRecyclerViewAdapter(profilList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profil_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = profilAdapter
            }
        }
        return view
    }

    fun replacePostList(list: List<DtoInputPost>) {
        profilList.clear()
        profilList.addAll(list)
        profilAdapter.notifyDataSetChanged()
    }

    fun addPost(dto: DtoInputPost) {
        profilList.add(dto)
        profilAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListPostFragment()
    }
}