package com.ekik.feature_home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_home.R
import com.ekik.feature_home.databinding.FragmentFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : BaseFragment<FragmentFormBinding>(R.layout.fragment_form) {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFormBinding {
        return FragmentFormBinding.inflate(inflater, container, false)
    }

    override fun subscribeUi() {

    }
}
