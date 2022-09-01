package id.kotlin.rickandmortyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.kotlin.rickandmortyproject.data.Resource
import id.kotlin.rickandmortyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpVM()
        getData()
    }

    private fun setUpVM() {
        val factory = ViewModelFactory.getInstance()
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun getData() {
        mainViewModel.getAllCharacters()
        mainViewModel.characters.observe(this){
            when(it) {
                is Resource.Loading -> {
                    if(it.state) {
                        binding.loadingIndicator.visibility = View.VISIBLE
                    }
                }
                is Resource.Success -> {
                    val layoutManager = LinearLayoutManager(this)
                    binding.rvCharacter.layoutManager = layoutManager
                    val adapter = CharacterAdapter(it.data.filterNotNull())
                    Log.d("MAIN", it.data.filterNotNull().size.toString())
                    binding.rvCharacter.adapter = adapter
                    binding.loadingIndicator.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.loadingIndicator.visibility = View.GONE
                    Log.d("MAIN", "MASUK ERROR")
                }
            }
        }

    }

}