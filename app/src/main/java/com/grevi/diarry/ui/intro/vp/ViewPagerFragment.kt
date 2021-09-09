package com.grevi.diarry.ui.intro.vp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.grevi.diarry.utils.SharedUtils
import com.grevi.diarry.databinding.FragmentViewPagerBinding
import com.grevi.diarry.ui.intro.IntroOneFragment
import com.grevi.diarry.ui.intro.IntroThreeFragment
import com.grevi.diarry.ui.intro.IntroTwoFragment
import com.grevi.diarry.ui.splash.SplashFragmentDirections

class ViewPagerFragment : Fragment() {

    private lateinit var binding : FragmentViewPagerBinding
    private lateinit var vpAdapter: ViewPagerAdapter
    private val fragments : List<Fragment> = listOf(IntroOneFragment(), IntroTwoFragment(), IntroThreeFragment())
    private lateinit var sharedUtils: SharedUtils
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpAdapter = ViewPagerAdapter(fragments, childFragmentManager, lifecycle)
        sharedUtils = SharedUtils(requireContext())
        navController = Navigation.findNavController(view)
        with(binding) {
            introVp.adapter = vpAdapter
            indicator.setViewPager2(introVp)
            introVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when(position) {
                        2 -> {
                            button.text = "Ok, Saya Mengerti"
                            button.setOnClickListener {
                                sharedUtils.setStartedKey()
                                ViewPagerFragmentDirections.actionViewPagerFragmentToHomeFragment().also {
                                    navController.navigate(it)
                                }
                            }
                        }

                        else -> {
                            button.text = "Selanjutnya"
                            button.setOnClickListener { introVp.currentItem += 1 }
                        }
                    }
                }
            })
        }
    }
}