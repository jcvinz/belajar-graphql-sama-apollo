package id.kotlin.rickandmortyproject.data
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.recyclerview.widget.DiffUtil
import id.kotlin.rickandmortyproject.MortyCharactersQuery

object DiffUtil : DiffUtil.ItemCallback<MortyCharactersQuery.Result>(){
    override fun areContentsTheSame(
        oldItem: MortyCharactersQuery.Result,
        newItem: MortyCharactersQuery.Result
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areItemsTheSame(
        oldItem: MortyCharactersQuery.Result,
        newItem: MortyCharactersQuery.Result
    ): Boolean {
        return oldItem == newItem
    }
}