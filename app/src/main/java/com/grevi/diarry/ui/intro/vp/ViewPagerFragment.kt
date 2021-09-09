package com.grevi.diarry.ui.intro.vp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.grevi.diarry.R
import com.grevi.diarry.databinding.FragmentViewPagerBinding
import com.grevi.diarry.ui.base.BaseFragment
import com.grevi.diarry.ui.intro.IntroOneFragment
import com.grevi.diarry.ui.intro.IntroThreeFragment
import com.grevi.diarry.ui.intro.IntroTwoFragment
import com.grevi.diarry.utils.SharedUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>() {

    private lateinit var vpAdapter: ViewPagerAdapter
    private val fragments : List<Fragment> = listOf(
        IntroOneFragment(),
        IntroTwoFragment(),
        IntroThreeFragment()
    )

    @Inject
    lateinit var sharedUtils: SharedUtils

    override fun viewBindingInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewPagerBinding {
        return FragmentViewPagerBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpAdapter = ViewPagerAdapter(fragments, childFragmentManager, lifecycle)
        initViewPager()
    }

    private fun initViewPager() = with(getViewBinding()) {
        introVp.adapter = vpAdapter
        indicator.setViewPager2(introVp)
        introVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position) {
                    2 -> {
                        button.text = getString(R.string.accept_intro_text)
                        button.setOnClickListener {
                            sharedUtils.setStartedKey()
                            ViewPagerFragmentDirections.actionViewPagerFragmentToHomeFragment().also {
                                getNavController().navigate(it)
                            }
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