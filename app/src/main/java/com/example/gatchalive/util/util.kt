package com.example.gatchalive.util

import android.app.Application
import android.content.Context
import android.view.ViewTreeObserver
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.gatchalive.MainViewModelGata
import com.example.gatchalive.SplashFragmentGata
import com.example.gatchalive.bookList.Gata
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import kotlinx.android.synthetic.main.splash_fragmentgata.*
import java.util.*

fun Fragment.removeFullScreenGata() {
    requireActivity().window.apply {
        addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}

fun MainViewModelGata.getStringGata(int: Int): String {
    return getApplication<Application>().resources.getString(int)
}

fun SplashFragmentGata.divideTextToPartsGata(gataList: List<Gata>) {
    content_tv_for_count.viewTreeObserver.addOnPreDrawListener(object :
        ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            content_tv_for_count.viewTreeObserver.removeOnPreDrawListener(this)

            val maxLinesVisible =
                content_tv_for_count.height / content_tv_for_count.lineHeight

            content_tv_for_count.maxLines = maxLinesVisible
            val start = content_tv_for_count.layout.getLineStart(0)
            val end = content_tv_for_count.layout.getLineEnd(maxLinesVisible - 1)

            val content = content_tv_for_count.text.toString().substring(start, end)
            gataList.forEach { book ->
                book.listOfContentPerPage = book.body.chunked(content.length)
            }
            return true
        }
    }
    )
}

fun initAddsGata(context: Context) {
    MobileAds.initialize(context) {}
    MobileAds.setRequestConfiguration(
        RequestConfiguration.Builder()
            .setTestDeviceIds(Arrays.asList("ABCDEF012345"))
            .build()
    )
}

fun initAdvertGata(context: Context): AdView {
    val adView = AdView(context)
    adView.adSize = AdSize.LARGE_BANNER
    adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
    return adView
}

//fun chunk(text: String, del: Int): List<String> {
//    val splitedList = mutableListOf<String>()
//    val charArray = text.toCharArray()
//    val endFor = charArray.size / del
//    var start = 0
//    var endIndex = del
//    var shifter = 0
//    for (x in 0..endFor) {
//        start = del * x + shifter
//        endIndex = start + del
//        val newCharArray = charArray.copyOfRange(start, endIndex).toMutableList()
//        while (!newCharArray[newCharArray.size - 1].equals(" ")) {
//            newCharArray.add(charArray[endIndex + 1])
//            shifter += 1
//        }
//        splitedList.add(newCharArray.toString())
//
//    }
//    return splitedList
//}