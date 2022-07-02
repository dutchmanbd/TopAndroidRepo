package com.test.topandroidrepo.presentation.fragments.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.test.topandroidrepo.R
import com.test.topandroidrepo.databinding.SimpleRepoItemBinding
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.presentation.activities.base.BaseAdapter
import com.test.utilities.DateTimeUtils
import javax.inject.Inject


class RepoAdapter @Inject constructor() : BaseAdapter<Repo, SimpleRepoItemBinding>() {
    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ) = SimpleRepoItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo) =
            oldItem.nodeId == newItem.nodeId

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
    }

    override fun onBindViewHolder(holder: BaseViewHolder<SimpleRepoItemBinding>, position: Int) {
        val repo = differ.currentList[position]
        holder.binding.apply {
            tvRepoName.text = repo.fullName
            val createdAt = DateTimeUtils.getLocalDate(repo.createdAt)
            val updatedAt = DateTimeUtils.getLocalDate(repo.updatedAt)
            tvCreatedAt.text = root.context.getString(R.string.created_at_format, createdAt)
            tvUpdatedAt.text = root.context.getString(R.string.updated_at_format, updatedAt)
            tvStar.text = repo.stargazersCount.toString()

            root.setOnClickListener { view ->
                listener?.let { click ->
                    click(view, repo)
                }
            }
        }
    }
}