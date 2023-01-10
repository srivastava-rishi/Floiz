package com.rsstudio.flobiz.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rsstudio.flobiz.R
import com.rsstudio.flobiz.data.network.model.Item
import com.rsstudio.flobiz.util.TimeUtil

class MainAdapter(
    private var context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var list: MutableList<Item> = mutableListOf()
    private var questionFilteredList: MutableList<Item> = mutableListOf()
    private var sortBy: Int = 0

    var logTag = "@MainAdapter"

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvQuestionTitle: TextView = view.findViewById(R.id.tvQuestionTitle)
        var tvUsername: TextView = view.findViewById(R.id.tvUsername)
        var tvPublicationTime: TextView = view.findViewById(R.id.tvPublicationTime)
        var userPic: ImageView = view.findViewById(R.id.civUserPic)


        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun onBind(item: Item,position: Int) {

            tvQuestionTitle.text = item.title
            tvUsername.text = item.owner.display_name
            tvPublicationTime.text = TimeUtil.getTime(item.creation_date)

            // setting image
            Glide
                .with(context)
                .load(item.owner.profile_image)
                .thumbnail(0.7f)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(userPic)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_question_data, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = list[position]
        if (holder is MainAdapter.ItemViewHolder) {
            holder.onBind(item,position)
        }
    }

    fun submitList(newList: List<Item>, sortType: Int) {
        list.clear()
        list.addAll(newList)
    }



    override fun getItemCount(): Int {
        if (list.size != 0) {
            return list.size
        }
        return 0
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

}


