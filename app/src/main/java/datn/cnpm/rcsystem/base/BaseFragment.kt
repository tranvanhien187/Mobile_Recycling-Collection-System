package datn.cnpm.rcsystem.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.common.extension.hideKeyboard
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            initData(arguments)
        }
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseBinding = FragmentBaseBinding.inflate(inflater, container, false)
        binding = bindingInflater.invoke(inflater, baseBinding.contentContainer, true)
        initViews()
        initActions()

        return baseBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        mNavController = NavHostFragment.findNavController(this)
        (activity as? BaseActivity<*>)?.setDecorFitSystemWindow(isDisableFullScreen())
        setStatusBarColor()
        setLightStatusBar()
        (requireActivity() as OnBackPressedDispatcherOwner)
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {
                onBackPressed()
            }
    }

    override fun onResume() {
        super.onResume()
        (activity as? BaseActivity<*>)?.applyInsetsListener(isDisableFullScreen())
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

    override fun showDialogConfirm(titleText: String, drawableId: Int, onConfirmClick: () -> Unit) {
        baseActivity?.showDialogConfirm(titleText, drawableId, onConfirmClick)
    }

    override fun hideDialogConfirm() {
        baseActivity?.hideDialogConfirm()
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

    open fun isDisableFullScreen() : Boolean = true

    open fun isLightStatusBar() : Boolean = true

    private fun setLightStatusBar() {
        (activity as? BaseActivity<*>)?.setLightStatusBar(isLightStatusBar())
    }

    internal fun setStatusBarColor(@ColorRes color: Int = R.color.grey_fafafa) {
        val colorStatusBar = if (!isDisableFullScreen()) {
            android.R.color.transparent
        } else {
            color
        }
        (activity as? BaseActivity<*>)?.setStatusBarColor(colorStatusBar)
    }
}
