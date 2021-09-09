package com.grevi.diarry.ui.main

import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.grevi.diarry.R
import com.grevi.diarry.databinding.ActivityMainBinding
import com.grevi.diarry.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun appBar(): AppBarLayout = getViewBinding().appBarLayout

    override fun toolBar(): MaterialToolbar = getViewBinding().toolbar

    override fun initViewBinding(
        layoutInflater: LayoutInflater
    ): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}