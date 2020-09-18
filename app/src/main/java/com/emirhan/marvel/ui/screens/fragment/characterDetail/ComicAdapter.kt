package com.emirhan.marvel.ui.screens.fragment.characterDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.emirhan.marvel.R
import com.emirhan.marvel.data.remote.ComicsSeriesEventsItem

class ComicAdapter(private val list: List<ComicsSeriesEventsItem>) :
    RecyclerView.Adapter<ViewHolderComic>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderComic =
        ViewHolderComic(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_comic, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolderComic, position: Int) {
        holder.textViewComicName.text = list[position].name
    }

    override fun getItemCount(): Int = list.size
}

class ViewHolderComic(view: View) : RecyclerView.ViewHolder(view) {
    val textViewComicName: AppCompatTextView = view.findViewById(R.id.textViewComicName)
}