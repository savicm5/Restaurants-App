package com.example.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurants.model.Result
import com.google.android.material.imageview.ShapeableImageView
import kotlin.math.roundToInt

class RestRecycleView(private val restaurantsList: ArrayList<com.example.restaurants.model.Result>) :
    RecyclerView.Adapter<RestRecycleView.MyViewHolder>() {


    private lateinit var mListener : onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.restaurant,parent,false)
        return MyViewHolder(itemView,this.mListener,restaurantsList)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = restaurantsList[position]
//        holder.titleImage.setImageResource(currentItem.titleImage)
        if(currentItem.images.isEmpty()){
            val url = "https://i.insider.com/596e3d09dde1891cc024879d?width=1000&format=jpeg&auto=webp"
            Glide.with(holder.titleImage.context)
                .load(url)
                .into(holder.titleImage)
        }
        else {
            val url = currentItem.images.first().source_url
            Glide.with(holder.titleImage.context)
                .load(url)
                .into(holder.titleImage)
        }
        //holder.titleImage.setImageResource(currentItem.images.first().source_url)
        holder.tvHeading.text = currentItem.name
        holder.adress.text = currentItem.location_id
        holder.rating.text = ((currentItem.score*100).roundToInt()/100.0).toString()
    }

    override fun getItemCount(): Int {

        return restaurantsList.size

    }

    //ako imam vise elemenata u jednom itemu moram sve da ih navedem ovde
    class MyViewHolder(itemView: View, listener:onItemClickListener, list: ArrayList<Result>) : RecyclerView.ViewHolder(itemView){

        val titleImage : ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading : TextView = itemView.findViewById(R.id.tvHeading)
        val adress: TextView = itemView.findViewById(R.id.textView4)
        val rating: TextView = itemView.findViewById(R.id.textView5)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(list[adapterPosition])
            }
        }

    }

    interface onItemClickListener {

        fun onItemClick(restaurantView: Result)

    }

    fun setOnItemClickListener(listener: onItemClickListener){
        this.mListener = listener
    }


}


