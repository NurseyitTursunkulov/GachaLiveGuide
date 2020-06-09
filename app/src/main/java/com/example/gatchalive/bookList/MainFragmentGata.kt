package com.example.gatchalive.bookList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gatchalive.MainViewModelGata
import com.example.gatchalive.R
import com.example.gatchalive.databinding.FragmentMaingataBinding
import com.example.gatchalive.util.EventObserver
import com.example.gatchalive.util.removeFullScreenGata
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragmentGata : Fragment() {

    lateinit var viewDataBinding: FragmentMaingataBinding
    lateinit var listAdapterGata: TasksAdapterGata
    val viewModelGata: MainViewModelGata by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        removeFullScreenGata()
        (activity as AppCompatActivity).supportActionBar?.show()
        viewDataBinding = FragmentMaingataBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModelGata
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelGata.navigateToDetailEvent.observe(viewLifecycleOwner,
            EventObserver {
                findNavController().navigate(R.id.action_mainFragment_to_bookDetailFragment)
            })
        viewModelGata.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            if (viewModelGata.interstitialAd.isLoaded) {
                viewModelGata.interstitialAd.show()
            } else {
                Log.d("Nurs", "mainfrag The interstitial wasn't loaded yet.")
            }
        })
        initAdapterGata()
    }

    override fun onPause() {
        viewModelGata.adView?.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModelGata.adView?.resume()
    }

    override fun onDestroy() {
        viewModelGata.adView?.destroy()
        super.onDestroy()
    }
}