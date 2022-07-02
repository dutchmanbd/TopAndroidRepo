package com.test.topandroidrepo.presentation.fragments.repos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.extensions.view.showSnackBar
import com.test.topandroidrepo.R
import com.test.topandroidrepo.databinding.FragmentRepoListBinding
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.presentation.fragments.base.BaseFragment
import com.test.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepoListFragment : BaseFragment<RepoListViewModel, FragmentRepoListBinding>(
    R.layout.fragment_repo_list
) {

    @Inject
    lateinit var repoAdapter: RepoAdapter

    override val viewModel by viewModels<RepoListViewModel>()

    override fun initializeViewBinding(view: View) = FragmentRepoListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeObservers()
    }

    private fun setupRecyclerView() {
        binding.rvRepos.adapter = repoAdapter
        repoAdapter.setOnItemClickListener { _, item ->
            navigateToDetailFragment(item)
        }
    }

    private fun navigateToDetailFragment(repo: Repo) {
        findNavController().navigate(
            RepoListFragmentDirections.actionRepoListFragmentToDetailFragment(
                repo
            )
        )
    }

    private fun subscribeObservers() {
        viewModel.getRepos().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Failure -> {
                    binding.piRepos.hide()
                    binding.root.showSnackBar(resource.message)
                }
                is Resource.Loading -> {
                    binding.piRepos.show()
                }
                is Resource.Success -> {
                    binding.piRepos.hide()
                    repoAdapter.differ.submitList(resource.data)
                }
            }
        }
    }


    companion object {
        private const val TAG = "RepoListFragment"
    }


}