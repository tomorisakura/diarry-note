package com.grevi.diarry.ui.task

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.grevi.diarry.R
import com.grevi.diarry.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    private lateinit var binding : FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater)
        return binding.root
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