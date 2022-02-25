package com.printoverit.machinetestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.printoverit.machinetestapp.databinding.SingleItemLayoutBinding
import com.printoverit.machinetestapp.dataclasses.Data
import com.printoverit.machinetestapp.dataclasses.Meme
import com.printoverit.machinetestapp.dataclasses.MemeDataClass

class TestAdapters : RecyclerView.Adapter<TestAdapters.MyViewHolder>() {
    private var memesss = emptyList<Meme>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMeme=memesss[position]
        holder.bind(currentMeme)
    }

    override fun getItemCount(): Int {
        return memesss.size
    }

    class MyViewHolder(private val binding: SingleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Meme) {
            binding.meme = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SingleItemLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    fun setData(newData: Data){
        memesss=newData.memes
    }

}