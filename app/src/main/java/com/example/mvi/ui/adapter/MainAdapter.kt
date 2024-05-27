package com.example.mvi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvi.data.model.FakeDTO
import com.example.mvi.databinding.UserViewHolderBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>(){

    private var list = mutableListOf<FakeDTO>()
    fun addItems(list : List<FakeDTO>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding : UserViewHolderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = this.list[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.tvDesc.text = item.body
    }
}