package net.simplifiedcoding.ui.movies

import android.view.View
import com.images.unsplashsampleapp.data.model.DataModel

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, dataModel: DataModel)
}