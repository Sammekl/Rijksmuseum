package com.sammekleijn.rijksmuseum.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val topLevelDestinationIds = listOf(
        R.id.nav_overview,
        R.id.nav_details,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigationComponent()
    }

    private fun setupNavigationComponent() {
        val navController = findNavController(R.id.navigation_host_fragment)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinationIds.toSet())
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            viewModel.onDestinationChanged(destination.id)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.navigation_host_fragment).navigateUp()
        return true
    }
}
