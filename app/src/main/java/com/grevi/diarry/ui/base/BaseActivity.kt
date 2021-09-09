package com.grevi.diarry.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.grevi.diarry.R

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {
    private lateinit var binding: T
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    abstract fun initViewBinding(layoutInflater: LayoutInflater): T
    abstract fun appBar(): AppBarLayout
    abstract fun toolBar(): MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolBar())
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.intro_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        initHostFragment()
    }

    private fun initHostFragment() {

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.splashFragment -> setToolbarVisible(false)
                R.id.viewPagerFragment -> setToolbarVisible(false)
                R.id.homeFragment -> setToolbarVisible(true, getString(R.string.home_text))
                R.id.formFragment -> setToolbarVisible(true, getString(R.string.form_text))
                R.id.taskFragment -> setToolbarVisible(true, getString(R.string.task_task))
            }
        }
    }

    private fun setToolbarVisible(status: Boolean, title: String? = null) {
        if (status) {
            appBar().visibility = View.VISIBLE
            toolBar().title = title
        } else {
            appBar().visibility = View.GONE
        }
    }

    internal fun getViewBinding() = binding
    internal fun getNavController() = navController
}