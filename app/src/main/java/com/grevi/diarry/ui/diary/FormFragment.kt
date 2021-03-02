package com.grevi.diarry.ui.diary

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.awesomedialog.*
import com.google.android.material.snackbar.Snackbar
import com.grevi.diarry.R
import com.grevi.diarry.databinding.FragmentFormBinding
import com.grevi.diarry.ui.viewmodel.DiaryViewModel
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : Fragment() {

    private lateinit var binding : FragmentFormBinding
    private val diaryViewModel : DiaryViewModel by viewModels()
    private lateinit var navController: NavController
    private val args : FormFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(inflater)
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
        when(args.state) {
            true -> {
                menu.findItem(R.id.delete_icon).isVisible = false
                menu.findItem(R.id.task_icon).isVisible = false
            }
            else -> {
                menu.findItem(R.id.delete_icon).isVisible = true
                menu.findItem(R.id.task_icon).isVisible = false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete_icon -> {
                val diary = args.diary
                deleteDialog(diary!!.id)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        when(args.state) {
            true -> {
                with(binding){
                    btnSave.setOnClickListener {
                        val title = titleEd.text.toString()
                        val content = contentEd.text.toString()
                        permissionHandler(title, content)
                    }
                }
            }

            else -> {
                val diary = args.diary
                with(binding) {
                    titleEd.setText(diary?.title)
                    contentEd.setText(diary?.content)

                    btnSave.setOnClickListener {
                        val title = titleEd.text.toString()
                        val content = contentEd.text.toString()
                        diaryViewModel.updateDiary(title, content, diary!!.id)

                        FormFragmentDirections.actionFormFragmentToHomeFragment().apply {
                            navController.navigate(this)
                        }
                    }
                }
            }
        }
    }

    private fun permissionHandler(title : String, content : String) {
        PermissionX.init(this)
            .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            .request { allGranted, _, _ ->
                if (allGranted) {
                    diaryViewModel.insertDiary(title, content)
                    FormFragmentDirections.actionFormFragmentToHomeFragment().apply {
                        navController.navigate(this)
                    }
                } else {
                    snackBar("Permission ditolak").show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        activity?.finish()
                    }, 1000)
                }
            }
    }

    private fun deleteDialog(id : String) {
        AwesomeDialog.build(requireActivity())
            .title("Hapus Diary ?", titleColor =  ContextCompat.getColor(requireContext(), R.color.lush_white))
            .body("Apakah kamu yakin ingin menghapus diary ini ?", color = ContextCompat.getColor(requireContext(), R.color.lush_white))
            .background(R.drawable.bg_dialog)
            .onPositive("Batal",
                buttonBackgroundColor = R.drawable.bg_dialog,
                textColor = ContextCompat.getColor(requireContext(), R.color.bronze_200))
            .onNegative("Hapus",
                buttonBackgroundColor = R.drawable.bg_dialog,
                textColor = ContextCompat.getColor(requireContext(), R.color.caution_red)) {
                diaryViewModel.deleteDiary(id)
                FormFragmentDirections.actionFormFragmentToHomeFragment().apply {
                    navController.navigate(this)
                }
            }
            .show()
    }

    private fun snackBar(msg : String) : Snackbar = Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT)
}
