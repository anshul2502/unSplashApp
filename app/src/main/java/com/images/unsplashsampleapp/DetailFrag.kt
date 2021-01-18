package com.images.unsplashsampleapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.images.unsplashsampleapp.data.model.DataModel
import com.images.unsplashsampleapp.databinding.DetailFragBinding


class DetailFrag : Fragment() {

    private lateinit var mContext:Context
    private lateinit var dataModel:DataModel
    private lateinit var detailFrag : DetailFragBinding

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataModel = requireArguments().getParcelable("selected_data")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailFrag  = DataBindingUtil.inflate(inflater, R.layout.detail_frag, container, false)
        val view:View = detailFrag.root
        detailFrag.unsplashDetail = dataModel

        return view
    }
}