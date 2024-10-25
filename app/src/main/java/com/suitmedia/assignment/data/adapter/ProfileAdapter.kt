package com.suitmedia.assignment.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.suitmedia.assignment.data.model.Profile
import com.suitmedia.assignment.databinding.ListProfileBinding

class ProfileAdapter(private val profiles: MutableList<Profile>,
    private val onItemClick: (Profile) -> Unit): RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(binding: ListProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        val profileIV = binding.profileIV
        val nameTV = binding.nameTV
        val emailTV = binding.emailTV
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ListProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profiles[position]
        Glide.with(holder.itemView.context)
            .load(profile.avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.profileIV)
        holder.nameTV.text = ("${profile.firstName} ${profile.lastName}")
        holder.emailTV.text = profile.email
        holder.itemView.setOnClickListener{
            onItemClick(profile)
        }
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    fun addProfiles(newProfiles: List<Profile>) {
        profiles.addAll(newProfiles) // Add new profiles to the existing list
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}