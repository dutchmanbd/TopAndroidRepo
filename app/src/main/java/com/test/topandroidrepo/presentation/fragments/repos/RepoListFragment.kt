package com.test.topandroidrepo.presentation.fragments.repos

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.test.extensions.view.showSnackBar
import com.test.topandroidrepo.R
import com.test.topandroidrepo.databinding.FragmentRepoListBinding
import com.test.topandroidrepo.presentation.fragments.base.BaseFragment
import com.test.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : BaseFragment<RepoListViewModel, FragmentRepoListBinding>(
    R.layout.fragment_repo_list
) {
    override val viewModel by viewModels<RepoListViewModel>()

    override fun initializeViewBinding(view: View) = FragmentRepoListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopRepos().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Failure -> {
                    binding.root.showSnackBar(resource.message)
                }
                is Resource.Loading -> {
                    // Loading Progress Loader
                }
                is Resource.Success -> {
                    Log.e(TAG, "onViewCreated: ${resource.data}")
                }
            }
        }
    }



    companion object {
        private const val TAG = "RepoListFragment"
    }


}