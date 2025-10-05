package com.ekik.feature_home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_home.R
import com.ekik.feature_home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun subscribeUi() {

    }

}