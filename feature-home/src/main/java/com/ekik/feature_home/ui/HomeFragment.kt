package com.ekik.feature_home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_home.R
import com.ekik.feature_home.databinding.FragmentHomeBinding
import com.ekik.feature_home.ui.adapter.DiaryAdapter
import com.ekik.feature_home.viewmodel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val diaryViewModel : DiaryViewModel by viewModels()
    private val diaryAdapter : DiaryAdapter by lazy { DiaryAdapter() }
    private val job : Job by lazy { Job() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        lifecycleScope.launchWhenResumed {
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

                        }

                        btnAdd.setOnClickListener {

                        }
                    }

                    is DiaryViewModel.State.Loading -> {

                    }
                    is DiaryViewModel.State.Error -> {}
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
                        with(binding) {
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
                        binding.apply {
                            emptyItemGroup.visibility = View.GONE
                            itemGroup.visibility = View.GONE
                        }
                    }

                    is DiaryViewModel.State.Error -> {

                    }
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