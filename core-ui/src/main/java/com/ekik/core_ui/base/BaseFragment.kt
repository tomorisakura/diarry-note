package com.ekik.core_ui.base

import android.os.Bundle
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(isHasOptionMenu)
    }

    fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
        FragmentViewBinding(this, viewBindingFactory)

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