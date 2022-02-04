package com.example.differentapioperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.differentapioperations.adapter.MyAdapter
import com.example.differentapioperations.databinding.ActivityMainBinding
import com.example.differentapioperations.model.Post
import com.example.differentapioperations.repository.Repository
import com.example.differentapioperations.viewmodel.MainViewModel
import com.example.differentapioperations.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val myAdapter = MyAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[MainViewModel::class.java]
        //val myPost = Post("PK", 1234, "Prasanna Kumar", 123123)
        binding.GETButton.setOnClickListener {
            val userID = binding.userIdTxt.text.toString()
            viewModel.getCustomPosts(Integer.parseInt(userID), "id", "desc")
            viewModel.myCustomPosts.observe(this) { it ->
                if (it.isSuccessful) {
                    it.body()?.let { myAdapter.setData(it) }
                    Log.d("Main", it.body().toString())
                    Log.d("Main", it.code().toString())
                    //Log.d("Main", it.headers().toString())
                } else {
                    Toast.makeText(this, it.code(), Toast.LENGTH_SHORT).show()
                }
            }
        }

/*
  val options: HashMap<String, String> = hashMapOf()
        options["_sort"] = "id"
        options["_order"] = "desc"
        binding.button.setOnClickListener {
            val myNumber = binding.numberEditText.text.toString()
            viewModel.getCustomPosts2(Integer.parseInt(myNumber), options)
            //sort and order are hardcoded. "desc"
            // viewModel.getPost()
            viewModel.myCustomPosts2.observe(this) { it ->
                if (it.isSuccessful) {
                    binding.textView.text = it.body().toString()
                    //binding.textView.text = it.body()?.title.toString()
                    it.body()?.forEach {
                        Log.d("Response", it.userId.toString())
                        Log.d("Response", it.body)
                        Log.d("Response", it.title)
                        Log.d("Response", it.id.toString())
                        Log.d("Response", "----------------------")
                    }
                } else {
                    Log.d("Response", it.errorBody().toString())
                    binding.textView.text = it.code().toString()
                }
            }
        }
 */

    }

    private fun setUpRecyclerView() {
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}