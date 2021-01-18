package com.images.unsplashsampleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.images.unsplashsampleapp.R
import com.images.unsplashsampleapp.data.model.DataModel
import com.images.unsplashsampleapp.databinding.RvImageViewBinding
import net.simplifiedcoding.ui.movies.RecyclerViewClickListener
import java.util.*
import kotlin.collections.ArrayList


class UnSplashAdapter(private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<UnSplashAdapter.UnSplashAdapterViewHolder>(),
    Filterable {
    private var dataModelList: List<DataModel>?= null
    private var dataModelListFull: List<DataModel>?= null


    inner class UnSplashAdapterViewHolder(
        val rvImageViewBinding: RvImageViewBinding
    ) : RecyclerView.ViewHolder(rvImageViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UnSplashAdapterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_image_view,
                parent,
                false
            )
        )


    override fun getItemCount(): Int {
        if(dataModelList.isNullOrEmpty()){
            return 0
        }
        return dataModelList!!.size
    }

    override fun onBindViewHolder(holder: UnSplashAdapterViewHolder, position: Int) {
        dataModelList?.let {
            holder.rvImageViewBinding.unsplash = it[position]
            holder.rvImageViewBinding.ivSplashImg.setOnClickListener {
                listener.onRecyclerViewItemClick(holder.rvImageViewBinding.ivSplashImg, dataModelList!![position])
            }
            holder.rvImageViewBinding.tvTitle.setOnClickListener {
                listener.onRecyclerViewItemClick(holder.rvImageViewBinding.tvTitle, dataModelList!![position])
            }
        }

    }

    //updating the view by calling notifyDataSetChanged
    internal fun setBookmark(dataModel: List<DataModel>?) {
        this.dataModelList = dataModel
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataModelListFull = dataModelList
                }else{
                    val resultList = ArrayList<DataModel>()
                    for (row in dataModelList!!) {
                        if (row.title.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    dataModelListFull = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataModelListFull
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataModelListFull = results?.values as ArrayList<DataModel>
                if(dataModelListFull.isNullOrEmpty()) {
                    setBookmark(dataModelListFull)
                }else{
                    setBookmark(dataModelList)
                }
            }
        }
    }
}