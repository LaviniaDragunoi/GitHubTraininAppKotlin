package com.example.githubtraininappkotlin.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.models.GithubRepoEntity

class ReposAdapter: RecyclerView.Adapter<ReposAdapter.ViewHolder>(){

    var repos = listOf<GithubRepoEntity>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
      val item = repos[position]

        holder.repoName.text = item.name
        holder.repoCreatedAt.text = item.createdAt
        holder.repoUpdated.text = item.updatedAt

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.repo_item, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val repoName: TextView = itemView.findViewById(R.id.repo_name)
        val repoCreatedAt: TextView = itemView.findViewById(R.id.repo_created)
        val repoUpdated: TextView = itemView.findViewById(R.id.repo_updated)
    }
}
