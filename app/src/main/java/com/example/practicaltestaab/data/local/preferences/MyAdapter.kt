package com.example.practicaltestaab.data.local.preferences

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.practicaltestaab.databinding.ItemBanksBinding
import com.example.practicaltestaab.domain.model.Quote
import com.squareup.picasso.Picasso

class MyAdapter(private val context: Context, private val arrayList: List<Quote>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemBanksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val convertView: View?
        convertView = binding.root
        binding.tvTittle.text = arrayList[position].bankName
        binding.tvYear.text = "${arrayList[position].age}"
        binding.tvDescription.text = arrayList[position].description

        Picasso.get().load(arrayList[position].url).into(binding.imageBank)

        return convertView
    }

}