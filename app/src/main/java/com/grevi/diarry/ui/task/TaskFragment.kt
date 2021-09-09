package com.grevi.diarry.ui.task

import android.os.Bundle
import android.view.*
import com.grevi.diarry.R
import com.grevi.diarry.databinding.FragmentTaskBinding
import com.grevi.diarry.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment : BaseFragment<FragmentTaskBinding>() {

    override fun viewBindingInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTaskBinding {
        return FragmentTaskBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.delete_icon).isVisible = false
        menu.findItem(R.id.task_icon).isVisible = false
    }
}