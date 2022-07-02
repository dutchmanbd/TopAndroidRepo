package com.test.topandroidrepo.presentation.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.test.extensions.view.showSnackBar
import com.test.topandroidrepo.R
import com.test.topandroidrepo.databinding.FragmentDetailBinding
import com.test.topandroidrepo.domain.model.User
import com.test.topandroidrepo.presentation.fragments.base.BaseFragment
import com.test.utilities.DateTimeUtils
import com.test.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>(
    R.layout.fragment_detail
) {
    private val args by navArgs<DetailFragmentArgs>()

    private val repo by lazy {
        args.repo
    }

    override val viewModel by viewModels<DetailViewModel>()

    override fun initializeViewBinding(view: View) = FragmentDetailBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getUser(repo.owner?.username).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Failure -> {
                    binding.piDetail.hide()
                    binding.root.showSnackBar(resource.message)
                }
                is Resource.Loading -> {
                    binding.piDetail.show()
                }
                is Resource.Success -> {
                    binding.piDetail.hide()
                    updateInformation(resource.data)
                }
            }
        }
    }

    private fun updateInformation(user: User) {
        binding.tvName.text = user.name
        binding.tvRepoDescription.text = repo.description
        binding.tvLastUpdate.text =
            getString(R.string.last_updated_at_format, DateTimeUtils.getLocalDate(repo.updatedAt))
        binding.ivAvatar.load(user.avatarUrl)
    }

}