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
import com.google.android.material.snackbar.Snackbar
import com.grevi.diarry.R
import com.grevi.diarry.databinding.FragmentHomeBinding
import com.grevi.diarry.ui.viewmodel.DiaryViewModel
import com.grevi.diarry.ui.home.adapter.DiaryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var navController: NavController
    private val diaryViewModel : DiaryViewModel by viewModels()
    private val diaryAdapter : DiaryAdapter by lazy { DiaryAdapter() }
    private val job : Job by lazy { Job() }

    private val TAG = HomeFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
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
            R.id.task_icon -> HomeFragmentDirections.actionHomeFragmentToTaskFragment().apply {
                navController.navigate(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        lifecycleScope.launchWhenCreated {
            diaryViewModel.allDiary.collect { state ->
                when(state) {
                    is DiaryViewModel.State.Success -> {
                        with(binding) {
                            rvListDiary.setHasFixedSize(true)
                            rvListDiary.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
                            rvListDiary.adapter = diaryAdapter
                            diaryAdapter.addItem(state.data)
                            diaryAdapter.onItemClicked = { diary ->
                                HomeFragmentDirections.actionHomeFragmentToFormFragment(false, diary).apply {
                                    navController.navigate(this)
                                }
                            }
                        }

                        binding.btnAdd.setOnClickListener {
                            HomeFragmentDirections.actionHomeFragmentToFormFragment(true, null).apply {
                                navController.navigate(this)
                            }
                        }
                    }

                    is DiaryViewModel.State.Loading -> {
                        snackBar(state.msg).show()
                    }
                    is DiaryViewModel.State.Error -> snackBar(state.exception.toString()).show()
                    else -> Log.v(TAG, "")
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
                        binding.emptyItemGroup.visibility = View.GONE
                        binding.itemGroup.visibility = View.GONE
                    }

                    is DiaryViewModel.State.Error -> Log.e(TAG, state.exception.toString())
                    else -> Log.d(TAG, "")
                }
            }
        }
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }

    private fun snackBar(msg : String) : Snackbar = Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT)
}