package com.obss.beymen.helpers

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.obss.beymen.helpers.Utilities.downloadFromUrl
import com.obss.beymen.helpers.Utilities.downloadLocaleFromUrl

object Extension {

    @BindingAdapter("android:downloadUrl")
    @JvmStatic
    fun downloadImage(view: ImageView, url: String?) { // custom imageview function
        view.downloadFromUrl(url, Utilities.placeHolderProgressBar(view.context))
    }

    @BindingAdapter("android:downloadLocaleFromUrl")
    @JvmStatic
    fun downloadLocaleFromUrl(view: ImageView, url: String) { // custom locale imageview function
        view.downloadLocaleFromUrl(url, Utilities.placeHolderProgressBar(view.context))
    }

    @BindingAdapter(value = ["htmlText"])
    @JvmStatic
    fun TextView.setHtmlText(string: String?) {
        text = HtmlCompat.fromHtml(string?:"", HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

}