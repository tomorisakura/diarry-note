package com.ekik.core_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseFragment<T: ViewBinding>(@LayoutRes layout: Int) : Fragment(layout) {

    protected var isHasOptionMenu = true
    private val superJob = SupervisorJob()
    private val coroutine = CoroutineScope(Dispatchers.Main + superJob)
    protected val binding = BindingInitializeImpl<T>()

    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): T

    abstract fun subscribeUi()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.initBinding(initBinding(inflater, container), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(isHasOptionMenu)
        subscribeUi()
    }

    fun runDelay(duration: Long = DURATION, callback: () -> Unit) {
        coroutine.launch {
            delay(duration)
            callback()
        }
    }

    companion object {
        private const val DURATION = 2000L
    }
}