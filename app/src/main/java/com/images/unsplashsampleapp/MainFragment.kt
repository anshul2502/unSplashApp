package com.images.unsplashsampleapp

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weatherreport.api.Status
import com.images.unsplashsampleapp.ViewModel.ImageViewModel
import com.images.unsplashsampleapp.adapter.UnSplashAdapter
import com.images.unsplashsampleapp.data.model.DataModel
import com.images.unsplashsampleapp.databinding.UnsplashGalleryFragBinding
import net.simplifiedcoding.ui.movies.RecyclerViewClickListener


class MainFragment : Fragment(), RecyclerViewClickListener,SearchView.OnQueryTextListener {

    private lateinit var viewModel: ImageViewModel
    private lateinit var mContext:Context
    private var views: View? = null
    private lateinit var unSplashAdapter : UnSplashAdapter
    private lateinit var navController:NavController
    private lateinit var bunding:UnsplashGalleryFragBinding

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(views==null){

            setHasOptionsMenu(true)

            bunding  = DataBindingUtil.inflate(inflater, R.layout.unsplash_gallery_frag, container, false)
            views = bunding.root

            bunding.rvImage.layoutManager = LinearLayoutManager(requireContext())
            bunding.rvImage.setHasFixedSize(true)
            unSplashAdapter = UnSplashAdapter(this)
            bunding.rvImage.adapter = unSplashAdapter

            viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
            viewModel.getSplashImages()


        }

        return views
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.data!!.observe(viewLifecycleOwner, Observer { dataModelList ->
            when(dataModelList.status){
                Status.SUCCESS-> {
                    unSplashAdapter.setBookmark(dataModelList.data)
                }
                Status.ERROR->{
                    Toast.makeText(mContext, ""+dataModelList.message, Toast.LENGTH_SHORT).show()
                }Status.LOADING->{

                Toast.makeText(mContext, "Loading", Toast.LENGTH_SHORT).show()
            }
            }


        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option, menu)
        val menuInflater: MenuItem? = menu.findItem(R.id.action_search)
        val searchView = menuInflater!!.actionView as SearchView

        searchView.setOnQueryTextListener(this)

    }

    override fun onRecyclerViewItemClick(view: View, dataModel: DataModel) {
        when(view.id){
            R.id.ivSplashImg -> {
                val bundle = bundleOf("selected_data" to dataModel)
                navController.navigate(R.id.detailFrag,bundle)
            }
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {

        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        unSplashAdapter.filter.filter(p0)
        return true
    }

}