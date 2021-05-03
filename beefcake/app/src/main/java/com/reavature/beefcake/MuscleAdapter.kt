package com.reavature.beefcake

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reavature.beefcake.databinding.FragmentWorkoutsLayoutBinding
import com.reavature.beefcake.database.Workouts
import kotlinx.android.synthetic.main.fragment_workouts_layout.view.*

class MuscleAdapter(private var items: List<Workouts>) :
    RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder>() {

    fun setItems(data: ArrayList<Workouts>) {
        this.items = data
    }

    class MuscleViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        private var binding: FragmentWorkoutsLayoutBinding =
            FragmentWorkoutsLayoutBinding.bind(itemView)
        val workout_title = binding.etTitleOfWorkout
//        var favorite_button = binding.favoriteButton

        fun bind(workoutsDataClass: Workouts) {
            workout_title.text = workoutsDataClass.title

//            favorite_button.setOnClickListener {
//                if (favorite_button.isPressed) {
//                    if (workoutsDataClass.favorite == android.R.drawable.btn_star_big_off) {
//                        workoutsDataClass.favorite = android.R.drawable.btn_star_big_on
//                        favorite_button.setImageResource(workoutsDataClass.favorite)
//                    } else {
//                        workoutsDataClass.favorite = android.R.drawable.btn_star_big_off
//                        favorite_button.setImageResource(workoutsDataClass.favorite)
//                    }
//                }
//            }

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(workoutsDataClass.image)
                .into(itemView.img_workoutsss)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleViewHolder {
        return MuscleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_workouts_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MuscleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
       return items.size
    }

}


