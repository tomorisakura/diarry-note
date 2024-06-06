package com.ekik.feature_intro.ui

import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_intro.R
import com.ekik.feature_intro.databinding.FragmentIntroOneBinding

class IntroOneFragment : BaseFragment<FragmentIntroOneBinding>(R.layout.fragment_intro_one) {
    private val binding by viewBinding { FragmentIntroOneBinding.bind(it) }
}