package com.example.mobilesmallborther

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilesmallborther.databinding.FragmentFormCreateProfilBinding
import com.example.mobilesmallbrother.dtos.DtoOutputCreateAnimal

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FormCreateProfilFragment : Fragment() {
    private lateinit var binding: FragmentFormCreateProfilBinding
    private var callbackOnSubmit:((dto:DtoOutputCreateAnimal) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormCreateProfilBinding.inflate(layoutInflater, container, false)

        binding.btnFormCreateProfilFragmentSubmit.setOnClickListener {
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(callback: ((DtoOutputCreateAnimal) -> Unit)) =
            FormCreateProfilFragment().apply {
                callbackOnSubmit = callback
            }
    }
}