package com.ekik.feature_intro.ui.vp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_intro.R
import com.ekik.feature_intro.databinding.FragmentViewPagerBinding
import com.ekik.feature_intro.ui.IntroOneFragment
import com.ekik.feature_intro.ui.IntroThreeFragment
import com.ekik.feature_intro.ui.IntroTwoFragment
import com.ekik.feature_intro.ui.viewmodel.IntroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>(R.layout.fragment_view_pager) {

    private val binding by viewBinding(FragmentViewPagerBinding::bind)
    private val viewModel by viewModels<IntroViewModel>()

    private lateinit var vpAdapter: ViewPagerAdapter
    private val fragments : List<Fragment> = listOf(
        IntroOneFragment(),
        IntroTwoFragment(),
        IntroThreeFragment()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpAdapter = ViewPagerAdapter(fragments, childFragmentManager, lifecycle)
        initViewPager()
    }

    private fun initViewPager() = with(binding) {
        introVp.adapter = vpAdapter
        indicator.attachTo(introVp)
        introVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position) {
                    2 -> {
                        button.text = getString(R.string.accept_intro_text)
                        button.setOnClickListener {
                            viewModel.navigateToHome()
                        }
                    }
                    else -> {
                        button.text = getString(R.string.next_text)
                        button.setOnClickListener { introVp.currentItem += 1 }
                    }
                }
            }
        })
    }
}