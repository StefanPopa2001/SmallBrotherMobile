package com.example.mobilesmallborther

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesmallborther.databinding.FragmentProfilManagerBinding

class ProfilManagerFragment : Fragment() {
    private lateinit var viewModel: ProfilManagerViewModel
    private lateinit var binding: FragmentProfilManagerBinding
    private lateinit var listProfilFragment: ListProfilFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilManagerBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(ProfilManagerViewModel::class.java)
        listProfilFragment = childFragmentManager.findFragmentByTag("animauxList") as ListProfilFragment

        // Nous observons la liste
        viewModel.mutableLiveDataByIdClientAnimal.observe(viewLifecycleOwner) {
            Log.i("test",it.toString())
            if (it != null) {
                listProfilFragment.replaceAnimalList(it)
            }
        }

        viewModel.mutableLiveDataCreateAnimal.observe(viewLifecycleOwner) {
            listProfilFragment.addAnimal(it)
        }
        viewModel.launchFetchByIdClient(it)

        return binding.root
    }

    companion object {
        fun newInstance() = ProfilManagerFragment()
    }
}