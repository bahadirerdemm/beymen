package com.obss.beymen.helpers

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.obss.beymen.R
import com.obss.beymen.db.ProductDatabase
import com.obss.beymen.model.FavoriteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

object Utilities {
    var favoriteList: List<FavoriteModel> = arrayListOf()

    fun getFavoriteList(context: Context){
        CoroutineScope(IO).launch {
            val dao = ProductDatabase(context).productDao()
            favoriteList = dao.getAllFavorite()
        }
    }

    fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {
        val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.drawable.ic_launcher_foreground)

        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(url)
            .into(this)
    }


    fun ImageView.downloadLocaleFromUrl(url: String, progressDrawable: CircularProgressDrawable) {
        val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.drawable.ic_launcher_foreground)

        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(getImage(url, context))
            .into(this);
    }

    fun getImage(imageName: String?, context: Context): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
        return CircularProgressDrawable(context).apply {
            strokeWidth = 8f
            centerRadius = 40f
            start()
        }
    }

}