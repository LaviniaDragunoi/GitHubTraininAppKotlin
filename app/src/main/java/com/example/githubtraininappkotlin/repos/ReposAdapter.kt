package com.example.githubtraininappkotlin.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtraininappkotlin.Util.displayDate
import com.example.githubtraininappkotlin.databinding.RepoItemBinding
import com.example.githubtraininappkotlin.models.GithubRepoEntity

class ReposAdapter : ListAdapter<GithubRepoEntity,
        ReposAdapter.ViewHolder>(ReposDiffCallback()) {

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = getItem(position)
       holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: RepoItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GithubRepoEntity) {
            binding.repoName.text = item.name
            binding.repoCreated.text = displayDate(item.createdAt!!)
            binding.repoUpdated.text = displayDate(item.updatedAt!!)
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

class ReposDiffCallback : DiffUtil.ItemCallback<GithubRepoEntity>(){
    override fun areItemsTheSame(oldItem: GithubRepoEntity, newItem: GithubRepoEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubRepoEntity, newItem: GithubRepoEntity): Boolean {
        return oldItem == newItem
    }

}