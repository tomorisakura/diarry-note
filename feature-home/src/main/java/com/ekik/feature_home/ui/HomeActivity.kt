package com.ekik.feature_home.ui

import android.os.Bundle
import com.ekik.core_ui.base.BaseActivity
import com.ekik.feature_home.R
import com.ekik.feature_home.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val binding by viewBinding(ActivityHomeBinding::inflate)

    override var navHostContainerId: Int = R.id.home_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}