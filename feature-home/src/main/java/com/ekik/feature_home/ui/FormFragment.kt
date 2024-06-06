package com.ekik.feature_home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_home.R
import com.ekik.feature_home.databinding.FragmentFormBinding
import com.ekik.feature_home.viewmodel.DiaryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : BaseFragment<FragmentFormBinding>(R.layout.fragment_form) {

    private val binding by viewBinding(FragmentFormBinding::bind)
    private val diaryViewModel : DiaryViewModel by viewModels()
//    private val args : FormFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
//        when(args.state) {
//            true -> {
//                btnSave.setOnClickListener {
//                    val title = titleEd.text.toString()
//                    val content = contentEd.text.toString()
//                    permissionHandler(title, content)
//                }
//            }
//
//            else -> {
//                val diary = args.diary
//                titleEd.setText(diary?.title)
//                contentEd.setText(diary?.content)
//
//                btnSave.setOnClickListener {
//                    val title = titleEd.text.toString()
//                    val content = contentEd.text.toString()
//                    diaryViewModel.updateDiary(title, content, diary!!.id)
//
//                    FormFragmentDirections.actionFormFragmentToHomeFragment().apply {
//                        getNavController().navigate(this)
//                    }
//                }
//            }
//        }
    }

    private fun permissionHandler(title : String, content : String) {
//        PermissionX.init(this)
//            .permissions(
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            )
//            .request { allGranted, _, _ ->
//                if (allGranted) {
//                    diaryViewModel.insertDiary(title, content)
//                    FormFragmentDirections.actionFormFragmentToHomeFragment().apply {
//                        getNavController().navigate(this)
//                    }
//                } else {
//                    snackBar(getViewBinding().root , getString(R.string.permission_deny))
//                    Handler(Looper.getMainLooper()).postDelayed({
//                        activity?.finish()
//                    }, 1000L)
//                }
//            }
    }

    private fun deleteDialog(id : String) {
//        AwesomeDialog.build(requireActivity())
//            .title(getString(R.string.delete_diary_question),
//                titleColor =  ContextCompat.getColor(requireContext(), R.color.lush_white)
//            )
//            .body(getString(R.string.make_sure_delete_diary_question),
//                color = ContextCompat.getColor(requireContext(), R.color.lush_white)
//            )
//            .background(R.drawable.bg_dialog)
//            .onPositive(getString(R.string.cancel_text),
//                buttonBackgroundColor = R.drawable.bg_dialog,
//                textColor = ContextCompat.getColor(requireContext(), R.color.bronze_200))
//            .onNegative(getString(R.string.delete_text),
//                buttonBackgroundColor = R.drawable.bg_dialog,
//                textColor = ContextCompat.getColor(requireContext(), R.color.caution_red)) {
//                diaryViewModel.deleteDiary(id)
//                FormFragmentDirections.actionFormFragmentToHomeFragment().apply {
//                    getNavController().navigate(this)
//                }
//            }
//            .show()
    }
}
