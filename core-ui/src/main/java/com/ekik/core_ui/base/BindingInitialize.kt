package com.ekik.core_ui.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

class BindingInitializeImpl<VB: ViewBinding>(private var unbindViewReference: (() -> Unit)? = null) : BindingInitialize<VB>, DefaultLifecycleObserver {

    override val binding: VB get() = _binding ?: error("Binding is null")

    private var _binding: VB? = null

    override fun initBinding(
        binding: VB,
        fragment: Fragment,
        isNeedRefresh: Boolean
    ): View {
        if (isNeedRefresh) this._binding = binding
        fragment.viewLifecycleOwner.lifecycle.addObserver(this)
        return binding.root
    }

    override fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
        unbindViewReference?.invoke()
        unbindViewReference = null
        _binding = null
    }

}

interface BindingInitialize<VB: ViewBinding> {
    val binding: VB
    fun initBinding(binding: VB, fragment: Fragment, isNeedRefresh: Boolean = true): View
}