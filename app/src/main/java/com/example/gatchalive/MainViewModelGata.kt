package com.example.gatchalive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gatchalive.bookList.Gata
import com.example.gatchalive.util.Event
import com.example.gatchalive.util.getStringGata
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModelGata(application: Application) : AndroidViewModel(application) {
    private val _splashState = MutableLiveData<Event<SplashStateGata>>()
    val splashStateGata: LiveData<Event<SplashStateGata>> = _splashState

    var adView: AdView? = null
    lateinit var interstitialAd: InterstitialAd

    private val _showAdvertEvent: MutableLiveData<Event<Boolean>> =
        MutableLiveData<Event<Boolean>>()
    val showAdvertEvent: LiveData<Event<Boolean>> = _showAdvertEvent

    var showAdvertState = false

    private val _navigateToDetailEvent = MutableLiveData<Event<Gata>>()
    val navigateToDetailEvent: LiveData<Event<Gata>> = _navigateToDetailEvent

    private val _items = MutableLiveData<List<Gata>>().apply {
        value = getBooksListGata()
    }

    val items: LiveData<List<Gata>> = _items

    init {
        viewModelScope.launch {
            delay(3000)
            _splashState.postValue(
                Event(
                    SplashStateGata.MainActivityGata()
                )
            )
            showAdvertGata()
        }
    }

    fun showAdvertGata() {
        if (showAdvertState)
            _showAdvertEvent.postValue(Event(showAdvertState))
    }

    fun openBookGata(gata: Gata) {
        _navigateToDetailEvent.postValue(
            Event(
                gata
            )
        )
    }

    private fun getBooksListGata(): List<Gata> {
        return listOf(
            Gata(
                title = getStringGata(R.string.book1title),
                body = getStringGata(R.string.book1body),
                imageId = R.drawable.gata0
            ),
            Gata(
                title = getStringGata(R.string.book_1_title),
                body = getStringGata(R.string.book_1_body),
                imageId = R.drawable.gata1
            ),
            Gata(
                title = getStringGata(R.string.book_2_title),
                body = getStringGata(R.string.book_2_body),
                imageId = R.drawable.gata2
            ),
            Gata(
                title = getStringGata(R.string.book_3_title),
                body = getStringGata(R.string.book_3_body),
                imageId = R.drawable.gata3
            ),
            Gata(
                title = getStringGata(R.string.book_4_title),
                body = getStringGata(R.string.book_4_body),
                imageId = R.drawable.gata4
            ),
            Gata(
                title = getStringGata(R.string.book_5_title),
                body = getStringGata(R.string.book_5_body),
                imageId = R.drawable.gata5
            ),
            Gata(
                title = getStringGata(R.string.book_6_title),
                body = getStringGata(R.string.book_6_body),
                imageId = R.drawable.gata6
            ),
            Gata(
                title = getStringGata(R.string.book_7_title),
                body = getStringGata(R.string.book_7_body),
                imageId = R.drawable.gata7
            ),
            Gata(
                title = getStringGata(R.string.book_8_title),
                body = getStringGata(R.string.book_8_body),
                imageId = R.drawable.gata8
            ),
            Gata(
                title = getStringGata(R.string.book_9_title),
                body = getStringGata(R.string.book_9_body),
                imageId = R.drawable.gata9
            ),
            Gata(
                title = getStringGata(R.string.book_10_title),
                body = getStringGata(R.string.book_10_body),
                imageId = R.drawable.gata10
            ),
            Gata(
                title = getStringGata(R.string.book_11_title),
                body = getStringGata(R.string.book_11_body),
                imageId = R.drawable.gata11
            ),
            Gata(
                title = getStringGata(R.string.book_12_title),
                body = getStringGata(R.string.book_12_body),
                imageId = R.drawable.gata12
            ),
            Gata(
                title = getStringGata(R.string.book_13_title),
                body = getStringGata(R.string.book_13_body),
                imageId = R.drawable.gata13
            ),
            Gata(
                title = getStringGata(R.string.book_14_title),
                body = getStringGata(R.string.book_14_body),
                imageId = R.drawable.gata14
            ),
            Gata(
                title = getStringGata(R.string.book_15_title),
                body = getStringGata(R.string.book_15_body),
                imageId = R.drawable.gata15
            ),
            Gata(
                title = getStringGata(R.string.book_16_title),
                body = getStringGata(R.string.book_16_body),
                imageId = R.drawable.gata16
            ),
            Gata(
                title = getStringGata(R.string.book_17_title),
                body = getStringGata(R.string.book_17_body),
                imageId = R.drawable.gata17
            )
        )
    }
}

sealed class SplashStateGata {
    class MainActivityGata : SplashStateGata()
}
