package com.printoverit.machinetestapp.bindinadapters

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.printoverit.machinetestapp.dataclasses.Meme
import com.printoverit.machinetestapp.fragments.MainFragmentDirections
import android.widget.LinearLayout




class TestBindingAdapters {

    companion object{

        @BindingAdapter("loadImage")
        @JvmStatic
        fun imageLoader(imageView: ImageView,url:String )
        {
            imageView.load(url) {
                crossfade(600)
            }

        }

       @BindingAdapter("imageResizer")
       @JvmStatic
        fun imageResizer(imageView: ImageView , meme: Meme){
           val layoutParams = LinearLayout.LayoutParams(meme.width, meme.height)
           imageView.setLayoutParams(layoutParams)
        }

        @BindingAdapter("onclick")
        @JvmStatic
        fun onClick(layout:ConstraintLayout , meme: Meme){
            layout.setOnClickListener {
                val action= MainFragmentDirections.actionMainFragmentToMemeDetailsActivity(meme)
                layout.findNavController().navigate(action)
            }
        }
    }
}