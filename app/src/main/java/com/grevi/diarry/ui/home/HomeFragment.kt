package com.grevi.diarry.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.grevi.diarry.R
import com.grevi.diarry.databinding.FragmentHomeBinding
import com.grevi.diarry.ui.base.BaseFragment
import com.grevi.diarry.ui.home.adapter.DiaryAdapter
import com.grevi.diarry.ui.viewmodel.DiaryViewModel
import com.grevi.diarry.utils.snackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val diaryViewModel : DiaryViewModel by viewModels()
    private val diaryAdapter : DiaryAdapter by lazy { DiaryAdapter() }
    private val job : Job by lazy { Job() }

    override fun viewBindingInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.delete_icon).isVisible = false
        menu.findItem(R.id.task_icon).isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.task_icon -> HomeFragmentDirections.actionHomeFragmentToTaskFragment().also {
                getNavController().navigate(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() = with(getViewBinding()) {
        lifecycleScope.launchWhenCreated {
            diaryViewModel.allDiary.collect { state ->
                when(state) {
                    is DiaryViewModel.State.Success -> {
                        rvListDiary.apply {
                            setHasFixedSize(true)
                            layoutManager = GridLayoutManager(
                                requireContext(),
                                2,
                                GridLayoutManager.VERTICAL,
                                false)
                            adapter = diaryAdapter
                        }
                        diaryAdapter.addItem(state.data)
                        diaryAdapter.onItemClicked = { diary ->
                            HomeFragmentDirections
                                .actionHomeFragmentToFormFragment(false, diary).apply {
                                getNavController().navigate(this)
                            }
                        }

                        btnAdd.setOnClickListener {
                            HomeFragmentDirections
                                .actionHomeFragmentToFormFragment(true, null).apply {
                                getNavController().navigate(this)
                            }
                        }
                    }

                    is DiaryViewModel.State.Loading -> {
                        snackBar(root, state.msg)
                    }
                    is DiaryViewModel.State.Error -> snackBar(root, state.exception.toString())
                    else -> Unit
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main + job).launch {
            diaryViewModel.allDiary.collect { state ->
                when(state) {
                    is DiaryViewModel.State.Success -> {
                        with(getViewBinding()) {
                            if (state.data.isNullOrEmpty()) {
                                emptyItemGroup.visibility = View.VISIBLE
                                itemGroup.visibility = View.GONE
                                emptyText.text = "Kamu belum menuliskan \n" + "selembar Diary"
                            } else {
                                emptyItemGroup.visibility = View.GONE
                                itemGroup.visibility = View.VISIBLE
                            }
                        }
                    }

                    is DiaryViewModel.State.Loading -> {
                        getViewBinding().apply {
                            emptyItemGroup.visibility = View.GONE
                            itemGroup.visibility = View.GONE
                        }
                    }

                    is DiaryViewModel.State.Error ->
                        snackBar(getViewBinding().root, state.exception.toString())
                    else -> Unit
                }
            }
        }
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }
}