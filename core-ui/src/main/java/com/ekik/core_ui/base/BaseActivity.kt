package com.ekik.core_ui.base

import android.view.LayoutInflater
import android.view.Menu
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.ekik.core_ui.util.observeOrNull

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {

    protected lateinit var navController: NavController

    abstract var navHostContainerId: Int

    protected fun loadNavController(@IdRes navHostFragmentId: Int) =
        (supportFragmentManager.findFragmentById(navHostFragmentId) as NavHostFragment).navController

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    protected fun observeNavigation(viewModel: BaseActivityViewModel) {
        navController = loadNavController(navHostContainerId)
        viewModel.navigationCommand.observeOrNull(this) {
            it.getNavigationIfNotHandled()?.let { navigationCommand ->
                when (navigationCommand) {
                    is NavigationCommand.NavigateTo -> navigationTo(navigationCommand.destination)
                    is NavigationCommand.Back -> back()
                }
            }
        }
    }

    private fun navigationTo(direction: ActivityNavigator.Destination) {
        ActivityNavigator(this).navigate(direction, null, null, null)
    }

    private fun back() {
        ActivityNavigator(this).popBackStack()
    }

    inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) { bindingInflater(layoutInflater) }
}