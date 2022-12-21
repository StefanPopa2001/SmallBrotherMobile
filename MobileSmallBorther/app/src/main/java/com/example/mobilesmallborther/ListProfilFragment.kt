package com.example.mobilesmallborther

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesmallbrother.dtos.DtoInputAnimal

class ListProfilFragment : Fragment() {
    private val profilList: ArrayList<DtoInputAnimal> = arrayListOf()
    private val profilAdapter = ProfileRecyclerViewAdapter(profilList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profil_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = profilAdapter
            }
        }
        return view
    }

    fun replaceAnimalList(list: List<DtoInputAnimal>) {
        profilList.clear()
        profilList.addAll(list)
        profilAdapter.notifyDataSetChanged()
    }

    fun addAnimal(dto: DtoInputAnimal) {
        profilList.add(dto)
        profilAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListProfilFragment()
    }
}