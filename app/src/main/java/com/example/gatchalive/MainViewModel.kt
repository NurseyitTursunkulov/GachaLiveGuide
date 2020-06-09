package com.example.gatchalive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gatchalive.bookList.Book
import com.example.gatchalive.util.Event
import com.example.gatchalive.util.getString
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _splashState = MutableLiveData<Event<SplashState>>()
    val splashState: LiveData<Event<SplashState>> = _splashState

    var adView: AdView? = null
    lateinit var interstitialAd: InterstitialAd

    private val _showAdvertEvent: MutableLiveData<Event<Boolean>> =
        MutableLiveData<Event<Boolean>>()
    val showAdvertEvent: LiveData<Event<Boolean>> = _showAdvertEvent

    var showAdvertState = false

    private val _navigateToDetailEvent = MutableLiveData<Event<Book>>()
    val navigateToDetailEvent: LiveData<Event<Book>> = _navigateToDetailEvent

    private val _items = MutableLiveData<List<Book>>().apply {
        value = getBooksList()
    }

    val items: LiveData<List<Book>> = _items

    init {
        viewModelScope.launch {
            delay(3000)
            _splashState.postValue(
                Event(
                    SplashState.MainActivity()
                )
            )
            showAdvert()
        }
    }

    fun showAdvert() {
        if (showAdvertState)
            _showAdvertEvent.postValue(Event(showAdvertState))
    }

    fun openBook(book: Book) {
        _navigateToDetailEvent.postValue(
            Event(
                book
            )
        )
    }

    private fun getBooksList(): List<Book> {
        return listOf(
            Book(
                title = getString(R.string.book1title),
                body = getString(R.string.book1body),
                imageId = R.drawable.gata0
            ),
            Book(
                title = getString(R.string.book_1_title),
                body = getString(R.string.book_1_body),
                imageId = R.drawable.gata1
            ),
            Book(
                title = getString(R.string.book_2_title),
                body = getString(R.string.book_2_body),
                imageId = R.drawable.gata2
            ),
            Book(
                title = getString(R.string.book_3_title),
                body = getString(R.string.book_3_body),
                imageId = R.drawable.gata3
            ),
            Book(
                title = getString(R.string.book_4_title),
                body = getString(R.string.book_4_body),
                imageId = R.drawable.gata4
            ),
            Book(
                title = getString(R.string.book_5_title),
                body = getString(R.string.book_5_body),
                imageId = R.drawable.gata5
            ),
            Book(
                title = getString(R.string.book_6_title),
                body = getString(R.string.book_6_body),
                imageId = R.drawable.gata6
            ),
            Book(
                title = getString(R.string.book_7_title),
                body = getString(R.string.book_7_body),
                imageId = R.drawable.gata7
            ),
            Book(
                title = getString(R.string.book_8_title),
                body = getString(R.string.book_8_body),
                imageId = R.drawable.gata8
            ),
            Book(
                title = getString(R.string.book_9_title),
                body = getString(R.string.book_9_body),
                imageId = R.drawable.gata9
            ),
            Book(
                title = getString(R.string.book_10_title),
                body = getString(R.string.book_10_body),
                imageId = R.drawable.gata10
            ),
            Book(
                title = getString(R.string.book_11_title),
                body = getString(R.string.book_11_body),
                imageId = R.drawable.gata11
            ),
            Book(
                title = getString(R.string.book_12_title),
                body = getString(R.string.book_12_body),
                imageId = R.drawable.gata12
            ),
            Book(
                title = getString(R.string.book_13_title),
                body = getString(R.string.book_13_body),
                imageId = R.drawable.gata13
            ),
            Book(
                title = getString(R.string.book_14_title),
                body = getString(R.string.book_14_body),
                imageId = R.drawable.gata14
            ),
            Book(
                title = getString(R.string.book_15_title),
                body = getString(R.string.book_15_body),
                imageId = R.drawable.gata15
            ),
            Book(
                title = getString(R.string.book_16_title),
                body = getString(R.string.book_16_body),
                imageId = R.drawable.gata16
            ),
            Book(
                title = getString(R.string.book_17_title),
                body = getString(R.string.book_17_body),
                imageId = R.drawable.gata17
            )
        )
    }
}

sealed class SplashState {
    class MainActivity : SplashState()
}
