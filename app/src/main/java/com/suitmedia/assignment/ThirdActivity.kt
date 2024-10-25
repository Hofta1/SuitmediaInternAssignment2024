package com.suitmedia.assignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suitmedia.assignment.data.adapter.ProfileAdapter
import com.suitmedia.assignment.data.model.Profile
import com.suitmedia.assignment.databinding.ActivityThirdBinding
import com.suitmedia.assignment.objects.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var backBtn: Button
    private var currentPage: Int = 1
    private var perPage: Int = 10
    private var isLoading = false
    private var username: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileAdapter = ProfileAdapter(mutableListOf()){ profile: Profile ->  
            onProfileClick(profile)
        }
        binding.profileRecyclerView.adapter = profileAdapter
        binding.profileRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchProfile(currentPage, perPage)

        binding.profileRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && lastVisibleItemPosition == totalItemCount - 1) {
                    loadMoreData()
                }
            }
        })

        backBtn = binding.backButton

        backBtn.setOnClickListener{
            val resultIntent = Intent()
            Log.d("username: ",username)
            resultIntent.putExtra("username", username)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun loadMoreData() {
        if (!isLoading) {
            isLoading = true
            currentPage++ // Increment page number
            fetchProfile(currentPage, perPage) // Load next page of data
        }
    }

    private fun fetchProfile(page: Int, perPage: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getProfile("application/json", "", page, perPage)
                val newProfiles = response.data // Get new data from API

                withContext(Dispatchers.Main) {
                    profileAdapter.addProfiles(newProfiles) // Update adapter with new data
                    isLoading = false // Reset loading state
                }
            } catch (e: Exception) {
                e.printStackTrace() // Handle the error
                isLoading = false // Reset loading state on error
            }
        }
    }
    private fun onProfileClick(profile: Profile){
        username = ("${profile.firstName} ${profile.lastName}")
        Toast.makeText(this,"${username}",Toast.LENGTH_SHORT).show()
    }
}
