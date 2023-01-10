package com.rsstudio.flobiz.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.card.MaterialCardView
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

        var container: MaterialCardView = view.findViewById(R.id.mcvMain)


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

        val item = questionFilteredList[position]
        if (holder is MainAdapter.ItemViewHolder) {
            holder.container.animation = AnimationUtils.loadAnimation(context,R.anim.anim_fade_scale)
            holder.onBind(item,position)
        }
    }

    fun submitList(newList: List<Item>, sortType: Int) {
        list.clear()
        questionFilteredList.clear()
        list.addAll(newList)
        questionFilteredList.addAll(newList)
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        if (questionFilteredList.size != 0) {
            return questionFilteredList.size
        }
        return 0
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val charString = constraint?.toString() ?: ""

                if(charString.isEmpty()){
                    questionFilteredList.clear()
                    questionFilteredList.addAll(list)
                } else{

                    var filteredList:  MutableList<Item> = mutableListOf()

                    list.filter {
                        (it.title.lowercase().startsWith(constraint.toString().lowercase().trim()) || it.owner.display_name.lowercase().startsWith(constraint.toString().lowercase().trim()))
                    }.forEach{ filteredList.add(it)}
                    questionFilteredList = filteredList
                }

                return FilterResults().apply { values = questionFilteredList }

            }
            override fun publishResults(constraint: CharSequence, results: FilterResults?) {
                if (results!!.values != null) {
                    questionFilteredList = results.values as MutableList<Item>
                    notifyDataSetChanged()
                }

            }
        }

    }

}


