package com.example.gatchalive.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.gatchalive.MainViewModelGata
import com.example.gatchalive.R
import com.example.gatchalive.util.EventObserver
import com.example.gatchalive.util.removeFullScreenGata
import kotlinx.android.synthetic.main.detail_viewpagergata.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ScreenSlidePageFragment :
    Fragment(R.layout.detail_viewpagergata) {
    private lateinit var content: String
    private var position: Int = 0

    val viewModelGata: MainViewModelGata by sharedViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            content = it.getString(CONTENT, "")
            position = it.getInt(POSITION)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeFullScreenGata()
        (activity as AppCompatActivity).supportActionBar?.show()
        content_text_view.text = content
        toolbar.title = viewModelGata.navigateToDetailEvent.value?.peekContent()?.title
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        viewModelGata.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            showInterstitialAdvertSafeGata(viewModelGata.interstitialAd)
        })
        showBannerAdvertGata(ad_view_detail_pager, viewModelGata.showAdvertState)

        Glide
            .with(this)
            .load(getRandomImageGata())
            .fitCenter()
            .into(imageView)


        showRateMeDialogGata()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Nurs", "onResume pos $position")
        if (position % 2 == 0) {
            Log.d("Nurs", "pos if $position")
            viewModelGata.showAdvertGata()
        }
    }

    companion object {
        const val POSITION = "position_"
        const val CONTENT = "content_"

        @JvmStatic
        fun newInstance(position: Int, content: String) =
            ScreenSlidePageFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION, position)
                    putString(CONTENT, content)
                }
            }
    }
}