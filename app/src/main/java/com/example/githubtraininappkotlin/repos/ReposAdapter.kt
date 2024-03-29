package com.example.githubtraininappkotlin.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtraininappkotlin.databinding.RepoItemBinding
import com.example.githubtraininappkotlin.models.GithubRepoEntity

class ReposAdapter(val clickListener: GithubRepoEntityListener) : ListAdapter<GithubRepoEntity,
        ReposAdapter.ViewHolder>(ReposDiffCallback()) {

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getItem(position), clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: GithubRepoEntity,
            clickListener: GithubRepoEntityListener
        ) {
            binding.repo = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RepoItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ReposDiffCallback : DiffUtil.ItemCallback<GithubRepoEntity>() {
    override fun areItemsTheSame(oldItem: GithubRepoEntity, newItem: GithubRepoEntity): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: GithubRepoEntity, newItem: GithubRepoEntity): Boolean {
        return oldItem == newItem
    }
}

class GithubRepoEntityListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(repoEntity: GithubRepoEntity) = clickListener(repoEntity.id)
}