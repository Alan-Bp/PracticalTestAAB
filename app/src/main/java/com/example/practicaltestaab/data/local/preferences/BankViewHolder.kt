package com.example.practicaltestaab.data.local.preferences

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltestaab.databinding.RvBanksBinding
import com.squareup.picasso.Picasso

class BankViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = RvBanksBinding.bind(view)

    fun bind(image: String) {
        Picasso.get().load(image).into(binding.ivBanks)
    }
}