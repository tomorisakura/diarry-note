package com.ekik.feature_intro.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_intro.R
import com.ekik.feature_intro.databinding.FragmentIntroOneBinding

class IntroOneFragment : BaseFragment<FragmentIntroOneBinding>(R.layout.fragment_intro_one) {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIntroOneBinding {
        return FragmentIntroOneBinding.inflate(inflater, container, false)
    }

    override fun subscribeUi() = Unit

}