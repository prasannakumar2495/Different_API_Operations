package com.example.differentapioperations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.differentapioperations.R
import com.example.differentapioperations.databinding.RowLayoutBinding
import com.example.differentapioperations.model.Post

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var myList = emptyList<Post>()
    fun setData(newList: List<Post>) {
        /*
        this function will be called in the MainFunction, to notify when ever the UI changes.
         */
        myList = newList
        notifyDataSetChanged()
    }

    class MyViewHolder(binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val userId = binding.userIdTxt
        val id = binding.idTxt
        val title = binding.titleTxt
        val body = binding.bodyTxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowLayoutBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = myList[position]
        holder.userId.text = "userId: ${user.userId}"
        holder.id.text = "id: ${user.id}"
        holder.title.text = "title: ${user.title}"
        holder.body.text = "body: ${user.body}"
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}