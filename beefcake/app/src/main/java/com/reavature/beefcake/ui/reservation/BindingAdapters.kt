package com.reavature.beefcake.ui.reservation

import android.util.Log
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.net.toUri
import androidx.core.view.size
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reavature.beefcake.R
import com.reavature.beefcake.room_model.RoomResponse
import retrofit2.Response



/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUrl = imgUrl.toUri().buildUpon().scheme("https").build()


        Glide.with(imgView.context)
        .load(imgUrl).apply(RequestOptions().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)).into(imgView)
    }

}

    @BindingAdapter("listData")
    fun bindRecyclerView(recyclerView: RecyclerView, data: List<RoomResponse>?){

        val adapter = recyclerView.adapter as RoomRecyclerAdapter
        if(adapter.itemCount < 0){
            Log.v("Empty", "No Data")
        }

        adapter.submitList(data)

    }


