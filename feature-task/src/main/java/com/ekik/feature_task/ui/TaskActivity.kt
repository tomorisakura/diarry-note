package com.ekik.feature_task.ui

import android.os.Bundle
import com.ekik.core_ui.base.BaseActivity
import com.ekik.feature_task.R
import com.ekik.feature_task.databinding.ActivityTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskActivity : BaseActivity<ActivityTaskBinding>() {

    private val binding by viewBinding(ActivityTaskBinding::inflate)

    override var navHostContainerId: Int = R.id.task_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}