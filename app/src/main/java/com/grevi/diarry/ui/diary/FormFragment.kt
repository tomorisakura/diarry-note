package com.grevi.diarry.ui.diary

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.awesomedialog.*
import com.grevi.diarry.R
import com.grevi.diarry.databinding.FragmentFormBinding
import com.grevi.diarry.ui.base.BaseFragment
import com.grevi.diarry.ui.viewmodel.DiaryViewModel
import com.grevi.diarry.utils.snackBar
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : BaseFragment<FragmentFormBinding>() {

    private val diaryViewModel : DiaryViewModel by viewModels()
    private val args : FormFragmentArgs by navArgs()

    override fun viewBindingInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFormBinding {
        return FragmentFormBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun initView() = with(getViewBinding()) {
        when(args.state) {
            true -> {
                btnSave.setOnClickListener {
                    val title = titleEd.text.toString()
                    val content = contentEd.text.toString()
                    permissionHandler(title, content)
                }
            }

            else -> {
                val diary = args.diary
                titleEd.setText(diary?.title)
                contentEd.setText(diary?.content)

                btnSave.setOnClickListener {
                    val title = titleEd.text.toString()
                    val content = contentEd.text.toString()
                    diaryViewModel.updateDiary(title, content, diary!!.id)

                    FormFragmentDirections.actionFormFragmentToHomeFragment().apply {
                        getNavController().navigate(this)
                    }
                }
            }
        }
    }

    private fun permissionHandler(title : String, content : String) {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .request { allGranted, _, _ ->
                if (allGranted) {
                    diaryViewModel.insertDiary(title, content)
                    FormFragmentDirections.actionFormFragmentToHomeFragment().apply {
                        getNavController().navigate(this)
                    }
                } else {
                    snackBar(getViewBinding().root , getString(R.string.permission_deny))
                    Handler(Looper.getMainLooper()).postDelayed({
                        activity?.finish()
                    }, 1000L)
                }
            }
    }

    private fun deleteDialog(id : String) {
        AwesomeDialog.build(requireActivity())
            .title(getString(R.string.delete_diary_question),
                titleColor =  ContextCompat.getColor(requireContext(), R.color.lush_white)
            )
            .body(getString(R.string.make_sure_delete_diary_question),
                color = ContextCompat.getColor(requireContext(), R.color.lush_white)
            )
            .background(R.drawable.bg_dialog)
            .onPositive(getString(R.string.cancel_text),
                buttonBackgroundColor = R.drawable.bg_dialog,
                textColor = ContextCompat.getColor(requireContext(), R.color.bronze_200))
            .onNegative(getString(R.string.delete_text),
                buttonBackgroundColor = R.drawable.bg_dialog,
                textColor = ContextCompat.getColor(requireContext(), R.color.caution_red)) {
                diaryViewModel.deleteDiary(id)
                FormFragmentDirections.actionFormFragmentToHomeFragment().apply {
                    getNavController().navigate(this)
                }
            }
            .show()
    }
}
