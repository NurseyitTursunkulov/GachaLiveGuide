package com.example.gatchalive.bookList

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import java.util.*


fun MainFragmentGata.initAdapterGata() {
    val viewModel = viewDataBinding.viewmodel
    if (viewModel != null) {
        listAdapterGata = TasksAdapterGata(viewModel)
        viewDataBinding.recyclerViewBooks.adapter = listAdapterGata
        viewDataBinding.recyclerViewBooks.layoutManager = GridLayoutManager(requireContext(), 2)
        if (viewModel.showAdvertState)
            makeOneSpanForAdViewGata()
        viewModel.items.observe(viewLifecycleOwner, Observer {
            listAdapterGata.submitList(it)
        })
    } else {
        //            Timber.w("ViewModel not initialized when attempting to set up adapter.")
    }
}

class TaskDiffCallbackGata : DiffUtil.ItemCallback<Gata>() {

    override fun areItemsTheSame(oldItem: Gata, newItem: Gata): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gata, newItem: Gata): Boolean {
        return oldItem == newItem
    }

}

@BindingAdapter("imageResource")
fun setImageResourceGata(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

fun getAdRequestGata(): AdRequest? {
    val adRequest = AdRequest.Builder().build()
    val testDeviceIds = Arrays.asList("F5E4CD8EA025C4062D9E4BE54D002D25")
    val configuration =
        RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
    MobileAds.setRequestConfiguration(configuration)
    return adRequest
}

private fun MainFragmentGata.makeOneSpanForAdViewGata() {
    (viewDataBinding.recyclerViewBooks.layoutManager as GridLayoutManager)
        .spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (position) {
                0 -> 2
                else -> 1
            }
        }
    }
}
