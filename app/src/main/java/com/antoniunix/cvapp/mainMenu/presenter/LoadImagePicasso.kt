package com.antoniunix.cvapp.mainMenu.presenter

import android.widget.ImageView
import com.squareup.picasso.Picasso

class LoadImagePicasso:ImageProfile{

    override fun loadImageProfile(imageView: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()){
            Picasso.with(imageView.context).load(imageUrl)

                    .into(imageView)
        }
    }

}