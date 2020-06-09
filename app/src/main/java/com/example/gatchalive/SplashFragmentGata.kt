package com.example.gatchalive

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gatchalive.util.EventObserver
import com.example.gatchalive.util.divideTextToPartsGata
import com.example.gatchalive.util.initAdvertGata
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SplashFragmentGata : Fragment(R.layout.splash_fragmentgata) {

    private val viewModelGata: MainViewModelGata by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelGata.adView = initAdvertGata(requireContext())
        val content = viewModelGata.items.value
        content?.let { bookList ->
            divideTextToPartsGata(bookList)
        }

        viewModelGata.splashStateGata.observe(viewLifecycleOwner,
            EventObserver {
                when (it) {
                    is SplashStateGata.MainActivityGata -> {
                        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                    }
                }
            })
        viewModelGata.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            if (viewModelGata.interstitialAd.isLoaded) {
                viewModelGata.interstitialAd.show()
            } else {
                Log.d("Nurs", "splash The interstitial wasn't loaded yet.")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

}