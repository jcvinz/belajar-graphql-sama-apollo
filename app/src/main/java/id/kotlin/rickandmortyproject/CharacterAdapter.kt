package id.kotlin.rickandmortyproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.kotlin.rickandmortyproject.databinding.ItemLayoutBinding

class CharacterAdapter(private val list: List<MortyCharactersQuery.Result>) : RecyclerView.Adapter<CharacterAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.tvName.text = list[position].name
        holder.binding.tvCharStatus.text = list[position].status.plus(" ${list[position].species}")
        Glide.with(holder.itemView.context)
            .load(list[position].image)
            .into(holder.binding.ivAvatar)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ListViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}