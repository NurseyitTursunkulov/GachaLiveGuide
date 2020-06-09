package com.example.gatchalive.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.gatchalive.MainViewModelGata
import com.example.gatchalive.databinding.FragmentBookDetailgataBinding
import com.example.gatchalive.util.EventObserver
import kotlinx.android.synthetic.main.fragment_book_detailgata.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ScreenSlideFirstPageFragmentGata : Fragment() {
    lateinit var content: String
    lateinit var viewDataBinding: FragmentBookDetailgataBinding
    val viewModelGata: MainViewModelGata by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            content = it.getString(CONTENT, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelGata.showAdvertGata()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        viewDataBinding = FragmentBookDetailgataBinding.inflate(inflater, container, false).apply {
            bookInfo = viewModelGata.navigateToDetailEvent.value?.peekContent()
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content_text_view.text = content
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        showBannerAdvertGata(ad_view_detail, viewModelGata.showAdvertState)

        viewModelGata.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            showInterstitialAdvertSafeGata(viewModelGata.interstitialAd)
        })
        viewModelGata.navigateToDetailEvent.value?.peekContent()?.imageId?.let {
            Glide
                .with(this)
                .load(it)
                .fitCenter()
                .into(image)
        }
    }

    companion object {
        const val CONTENT = "content_"

        @JvmStatic
        fun newInstance(content: String) =
            ScreenSlideFirstPageFragmentGata().apply {
                arguments = Bundle().apply {
                    putString(CONTENT, content)
                }
            }
    }
}