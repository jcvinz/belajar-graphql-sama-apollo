package id.kotlin.rickandmortyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.kotlin.rickandmortyproject.data.Resource
import id.kotlin.rickandmortyproject.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setUpVM()
        setupRV()
        getData()
    }

    //    private fun setUpVM() {
//        val factory = ViewModelFactory.getInstance()
//        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
//    }
    private fun setupRV() {
        adapter = CharacterAdapter()
        val footerAdapter = LoadingStateAdapter {
            adapter.retry()
        }
        binding.rvCharacter.adapter = adapter.withLoadStateFooter(
            footer = footerAdapter
        )
        val layoutManager = LinearLayoutManager(this)
        binding.rvCharacter.layoutManager = layoutManager
    }

    private fun getData() {
//        mainViewModel.getAllCharacters()
//        mainViewModel.characters.observe(this){
//            when(it) {
//                is Resource.Loading -> {
//                    if(it.state) {
//                        binding.loadingIndicator.visibility = View.VISIBLE
//                    }
//                }
//                is Resource.Success -> {
//                    val layoutManager = LinearLayoutManager(this)
//                    binding.rvCharacter.layoutManager = layoutManager
//                    val adapter = CharacterAdapter(it.data.filterNotNull())
//                    binding.rvCharacter.adapter = adapter
//                    binding.loadingIndicator.visibility = View.GONE
//                }
//                is Resource.Error -> {
//                    binding.loadingIndicator.visibility = View.GONE
//                }
//            }
//        }
        mainViewModel.getListCharacter().observe(this) {
            binding.loadingIndicator.visibility = View.VISIBLE
            if (it != null) {
                binding.loadingIndicator.visibility = View.GONE
                adapter.submitData(lifecycle, it)
            }
        }


    }

}