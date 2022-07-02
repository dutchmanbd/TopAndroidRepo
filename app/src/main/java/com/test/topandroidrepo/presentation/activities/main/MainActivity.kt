package com.test.topandroidrepo.presentation.activities.main


import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.test.extensions.activity.findNavController
import com.test.topandroidrepo.R
import com.test.topandroidrepo.databinding.ActivityMainBinding
import com.test.topandroidrepo.domain.model.Repo
import com.test.topandroidrepo.presentation.activities.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController by lazy {
        findNavController(binding.navHostMainFragment.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupNavigationController()
    }

    private fun setupNavigationController() {
        setupActionBarWithNavController(navController, null)
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            if (destination.id == R.id.detailFragment) {
                supportActionBar?.apply {
                    title = (arguments?.getParcelable("repo") as Repo?)?.fullName
                        ?: getString(R.string.detail)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


    override fun initializeViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}