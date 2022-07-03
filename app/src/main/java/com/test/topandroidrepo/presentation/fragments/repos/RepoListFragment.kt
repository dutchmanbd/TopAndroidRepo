package com.test.topandroidrepo.presentation.fragments.repos

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.test.extensions.view.scrollTop
import com.test.extensions.view.showSnackBar
import com.test.topandroidrepo.R
import com.test.topandroidrepo.databinding.FragmentRepoListBinding
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.presentation.fragments.base.BaseFragment
import com.test.utilities.FilterItem
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
        setupListeners()
        setupMenuHostProvider()
    }

    private fun setupMenuHostProvider() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_filter, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.actionFilter) {
                    val menuItemView = requireActivity().findViewById<View>(R.id.actionFilter)
                    showFilterItems(menuItemView)
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showFilterItems(menuItemView: View) {
        val popupMenu = PopupMenu(requireContext(), menuItemView)
        popupMenu.menuInflater.inflate(R.menu.menu_filter_item, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.actionFilterByStars) {
                viewModel.updateFilter(FilterItem.Stars)
            } else if (item.itemId == R.id.actionFilterByUpdated) {
                viewModel.updateFilter(FilterItem.Updated)
            }
            true
        }
        popupMenu.show()
    }

    private fun setupListeners() {
        binding.etSearch.addTextChangedListener { text ->
            text?.toString()?.let {
                viewModel.updateQuery(it)
            }
        }
        binding.etSearch.setText(R.string.android)
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
        viewModel.repos.observe(viewLifecycleOwner) { resource ->
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
                    if (resource.data.isNotEmpty()) {
                        binding.rvRepos.scrollTop()
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "RepoListFragment"
    }


}