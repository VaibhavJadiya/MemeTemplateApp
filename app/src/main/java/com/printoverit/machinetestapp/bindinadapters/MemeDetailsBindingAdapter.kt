package com.printoverit.machinetestapp.bindinadapters

import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.printoverit.machinetestapp.dataclasses.Meme

class MemeDetailsBindingAdapter {
    companion object{

        @BindingAdapter("loadMeme")
        @JvmStatic
        fun loadMeme(imageView: ImageView,url:String){
            imageView.load(url){
                crossfade(600)
            }
        }

        @BindingAdapter("downloadCLick")
        @JvmStatic
        fun OnCLick(button: Button , meme: Meme){
            button.setOnClickListener {
                button.text="Done"
            }
        }
    }
}