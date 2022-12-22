package com.example.mobilesmallborther

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobilesmallborther.PostRecyclerViewAdapter.ViewHolder
import com.example.mobilesmallborther.databinding.FragmentPopupAnimalBinding
import com.example.mobilesmallborther.databinding.FragmentProfilListBinding
import com.example.mobilesmallbrother.dtos.DtoInputPost
import org.w3c.dom.Text

class DetailPostFragment(post : DtoInputPost): Fragment() {
    private lateinit var binding: FragmentPopupAnimalBinding
    private val postSelection = post
    private lateinit var viewModel: PostManagerViewModel

    var onItemOnClickListener:((item: DtoInputPost)-> Unit)? = null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPopupAnimalBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(PostManagerViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_popup_animal, container, false)
        val viewHolder = ViewHolder(view)
        onBindViewHolder(viewHolder)


        return view


    }

    inner class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameAnimal: TextView = itemView.findViewById(R.id.popup_animal_name)
        val descriptionView: TextView = itemView.findViewById(R.id.popup_animal_details)
        val datePost: TextView = itemView.findViewById(R.id.popup_animal_datepost)
        val townDisparition: TextView = itemView.findViewById(R.id.popup_animal_town)
        val typeAnimal: TextView = itemView.findViewById(R.id.popup_animal_typeAnimal)
        val animalBreed: TextView = itemView.findViewById(R.id.popup_animal_breed)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val cross: ImageView = itemView.findViewById(R.id.close_button)
        val btn: Button = itemView.findViewById(R.id.btn_contact)
        val namePeople: TextView = itemView.findViewById(R.id.popup_animal_proprio)

        init{
            cross.setOnClickListener {
                activity?.finish()
            }

            btn.setOnClickListener {


                    val mailReciever = postSelection.animalPost.clientAnimalPost.mail
                    val nameAnimal = postSelection.animalPost.nameAnimal
                    val datePost = postSelection.datePost

                    val selectorIntent = Intent(Intent.ACTION_SENDTO)
                    selectorIntent.data = Uri.parse("mailto:")

                    val emailIntent = Intent(Intent.ACTION_SEND)
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mailReciever))
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Disparition de $nameAnimal")
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Ce mail concerne le post de la disparition de $nameAnimal le $datePost")
                    emailIntent.selector = selectorIntent

                    startActivity(Intent.createChooser(emailIntent, "Send email..."))
                    true
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + descriptionView.text + "'"
        }
    }

    fun onBindViewHolder(holder: ViewHolder) {

        val item = postSelection
        holder.namePeople.text = item.animalPost.clientAnimalPost.mail
        holder.nameAnimal.text = item.animalPost.nameAnimal
        holder.descriptionView.text = item.descriptionPost
        holder.datePost.text = item.datePost
        holder.townDisparition.text = item.townDisparition
        holder.typeAnimal.text = item.animalPost.typeAnimal
        holder.animalBreed.text = item.animalPost.breed
        var url = item.urlImage
        Glide.with(holder.imageView)
            .load(url)
            .into(holder.imageView)
    }





}