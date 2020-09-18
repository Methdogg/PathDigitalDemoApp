package com.emirhan.marvel.ui.screens.fragment.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.emirhan.marvel.R
import com.emirhan.marvel.data.remote.MarvelCharacter
import com.emirhan.marvel.extension.load

class CharacterAdapter(
    private val characterList: MutableList<MarvelCharacter> = ArrayList(),
    private val onCharacterClickListener: ((MarvelCharacter) -> Unit) = {}
) : RecyclerView.Adapter<ViewHolderCharacter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter =
        ViewHolderCharacter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_character, parent, false),
            characterList,
            onCharacterClickListener
        )

    override fun onBindViewHolder(holder: ViewHolderCharacter, position: Int) {
        val item = characterList[position]

        holder.imageViewCharacter.load("${item.thumbnail?.path}/standard_medium.${item.thumbnail?.extension}")
        holder.textViewCharacterName.text = item.name
    }

    override fun getItemCount(): Int = characterList.size

    fun addItems(list: MutableList<MarvelCharacter>) {
        characterList.addAll(list)
        notifyItemInserted(characterList.lastIndex)
    }
}

class ViewHolderCharacter(
    view: View,
    characterList: MutableList<MarvelCharacter>,
    onCharacterClickListener: ((MarvelCharacter) -> Unit)
) : RecyclerView.ViewHolder(view) {
    val imageViewCharacter: AppCompatImageView = view.findViewById(R.id.imageViewCharacter)
    val textViewCharacterName: AppCompatTextView = view.findViewById(R.id.textViewCharacterName)

    init {
        view.setOnClickListener {
            onCharacterClickListener.invoke(characterList[adapterPosition])
        }
    }
}

interface OnCharacterClickListener {
    fun onCharacterClick(position: Int)
}