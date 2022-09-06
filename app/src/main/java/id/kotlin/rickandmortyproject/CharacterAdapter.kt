package id.kotlin.rickandmortyproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.kotlin.rickandmortyproject.data.DiffUtil
import id.kotlin.rickandmortyproject.databinding.ItemLayoutBinding

class CharacterAdapter() :
    PagingDataAdapter<MortyCharactersQuery.Result, CharacterAdapter.ListViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.tvName.text = data?.name
        holder.binding.tvCharStatus.text = data?.status.plus(" ${data?.species}")
        Glide.with(holder.itemView.context)
            .load(data?.image)
            .into(holder.binding.ivAvatar)
    }

    class ListViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}