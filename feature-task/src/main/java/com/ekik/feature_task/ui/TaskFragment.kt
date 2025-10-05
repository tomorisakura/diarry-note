package com.ekik.feature_task.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_task.R
import com.ekik.feature_task.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment : BaseFragment<FragmentTaskBinding>(R.layout.fragment_task) {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTaskBinding {
        return FragmentTaskBinding.inflate(inflater, container, false)
    }

    override fun subscribeUi() {

    }

}