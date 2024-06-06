package com.ekik.feature_intro.ui

import android.os.Bundle
import com.ekik.core_ui.base.BaseActivity
import com.ekik.feature_intro.R
import com.ekik.feature_intro.databinding.ActivityIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : BaseActivity<ActivityIntroBinding>() {

    private val binding by viewBinding(ActivityIntroBinding::inflate)

    override var navHostContainerId: Int = R.id.intro_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}