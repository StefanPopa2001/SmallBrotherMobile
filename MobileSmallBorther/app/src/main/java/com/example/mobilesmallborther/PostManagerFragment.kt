package com.example.mobilesmallborther

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesmallborther.databinding.FragmentPostManagerBinding

class PostManagerFragment : Fragment() {
    private lateinit var viewModel: PostManagerViewModel
    private lateinit var binding: FragmentPostManagerBinding
    private lateinit var listPostFragment: ListPostFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostManagerBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(PostManagerViewModel::class.java)
        listPostFragment = childFragmentManager.findFragmentByTag("postList") as ListPostFragment

        // Nous observons la liste
        viewModel.mutableLiveDataListPost.observe(viewLifecycleOwner) {
            Log.i("test",it.toString())
            listPostFragment.replacePostList(it)
        }

        viewModel.mutableLiveDataCreatePost.observe(viewLifecycleOwner) {
            listPostFragment.addPost(it)
        }

        // Nous lançons la requête
        viewModel.launchFetchAllPost()

        return binding.root
    }

    companion object {
        fun newInstance() = PostManagerFragment()
    }
}