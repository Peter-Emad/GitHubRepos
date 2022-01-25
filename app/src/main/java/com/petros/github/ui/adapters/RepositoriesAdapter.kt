package com.petros.github.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petros.github.R
import com.petros.github.data.model.Repository
import com.petros.github.ui.utils.loadUrl
import kotlinx.android.synthetic.main.item_repository.view.*


class RepositoriesAdapter(
    private val repositories: ArrayList<Repository>,
    private val onRepositoryClick: (Repository) -> Unit
) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {


    class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            repository: Repository,
            onRepositoryClick: (Repository) -> Unit
        ) {
            itemView.txtRepoTitle.text = repository.full_name
            itemView.txtRepoDescription.text = repository.description
            itemView.imgRepo.loadUrl(repository.owner.avatar_url)
            itemView.setOnClickListener { onRepositoryClick(repository) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepositoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repository, parent,
                false
            )
        )


    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) =
        holder.bind(repositories[position], onRepositoryClick)

    override fun getItemCount(): Int = repositories.size

    fun addRepositories(list: List<Repository>) {
        repositories.addAll(list)
        notifyDataSetChanged()
    }
    fun clearRepositories() {
        repositories.clear()
        notifyDataSetChanged()
    }
}