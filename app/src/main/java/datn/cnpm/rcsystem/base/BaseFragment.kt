package datn.cnpm.rcsystem.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import datn.cnpm.rcsystem.common.extension.hideKeyboard
import datn.cnpm.rcsystem.core.logging.DebugLog
import datn.cnpm.rcsystem.databinding.FragmentBaseBinding
import datn.cnpm.rcsystem.databinding.ToolBarBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment(), IBaseFragment, ToolBarController {
    override val baseActivity: BaseActivity<*>?
        get() = activity as? BaseActivity<*>

    override val toolbar: ToolBarBinding
        get() = baseBinding.layoutToolBar

    private lateinit var baseBinding: FragmentBaseBinding

    lateinit var binding: VB
        private set
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected var mNavController: NavController? = null
    protected var mRootView: View? = null
    private var mViewInitializedFlg = false

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DebugLog.i("screen: ${this.javaClass.simpleName}")
        if (mRootView == null) {
            baseBinding = FragmentBaseBinding.inflate(inflater, container, false)
            binding = bindingInflater.invoke(inflater, baseBinding.contentContainer, true)
            mRootView = baseBinding.root
            mViewInitializedFlg = true
        } else {
            mViewInitializedFlg = false
            // Remove view on the child's parent first.
            val parent = mRootView?.parent
            if (parent != null) {
                parent as ViewGroup
                parent.removeView(mRootView)
            }
        }
        return mRootView
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = NavHostFragment.findNavController(this)
        if (mViewInitializedFlg) {
            initViews()
            initActions()
            initData(arguments)
            initObservers()
        }
        (requireActivity() as OnBackPressedDispatcherOwner)
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {
                onBackPressed()
            }
    }

    override fun showLoading() {
        baseActivity?.showLoading()
    }

    override fun hideLoading() {
        baseActivity?.hideLoading()
    }

    override fun showError(errorString: String) {
        baseActivity?.showError(errorString)
    }

    override fun hideError() {
        baseActivity?.hideError()
    }

    override fun onBackPressed() {
        if (!findNavController().popBackStack()) {
            requireActivity().finish()
        }
    }

    /**
     * Adds a [Fragment] to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     * @param addBackStack    True/false add back stack.
     */
    protected open fun replaceFragment(
        containerViewId: Int,
        fragment: Fragment?,
        addBackStack: Boolean
    ) {
        val fragmentTransaction = this.childFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment!!)
        if (addBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    /**
     * This method using to back to parent fragment from child's
     */
    protected open fun popBackStackManager() {
        val fragmentManager = parentFragmentManager
        if (fragmentManager.backStackEntryCount != 0) {
            fragmentManager.popBackStack()
        }
    }

    /**
     * Hide keyboard and make all inputs out focus
     */
    protected fun clearAllInputsFocus() {
        binding.root.apply {
            requestFocus()
            hideKeyboard()
        }
    }

    internal fun reCreateActivitySmooth() {
        baseActivity?.reCreateActivitySmooth()
    }

    internal fun logoutApp() {
        baseActivity?.logoutApp()
    }

    internal fun setStatusAppNotLogin() {
        baseActivity?.setStatusAppNotLogin()
    }

    /**
     * This method using to back to previous screen with used navigation controller
     */
    internal fun popBackStackNavigate() {
        mNavController?.popBackStack()
    }

    override fun showToolbar(title: String, iconStart: Int?, iconEnd: Int?) {
        baseActivity?.supportActionBar?.hide()
        super.showToolbar(title, iconStart, iconEnd)
    }

    fun showActivityToolbar() {
        baseActivity?.supportActionBar?.show()
    }

    override fun onIconStartClicked() {
        super.onIconStartClicked()
        onBackPressed()
    }
}
