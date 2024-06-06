package com.ekik.feature_task.ui

import android.os.Bundle
import android.view.View
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_task.R
import com.ekik.feature_task.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment : BaseFragment<FragmentTaskBinding>(R.layout.fragment_task) {

    private val binding by viewBinding(FragmentTaskBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}