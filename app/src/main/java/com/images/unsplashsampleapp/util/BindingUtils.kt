package net.simplifiedcoding.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.images.unsplashsampleapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
   Picasso.get().load(url).placeholder(R.drawable.default_img).into(view)

}