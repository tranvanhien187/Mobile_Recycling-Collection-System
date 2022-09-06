package datn.cnpm.rcsystem.base

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import datn.cnpm.rcsystem.R
import datn.cnpm.rcsystem.common.extension.hideKeyboard
import datn.cnpm.rcsystem.databinding.FragmentBaseBinding

/**
 * This abstract class should be used for all bottom sheet dialog fragment.
 *
 * @param VB : the [ViewBinding].
 * @property baseActivity the [BaseActivity].
 * @property baseBinding the [FragmentBaseBinding].
 * @property binding the [VB].
 * @property bindingInflater the logic binding inflate.
 */
abstract class BaseBottomSheetDialogFragment<VB : ViewBinding> : BottomSheetDialogFragment(),
    IBaseFragment {
    override val baseActivity: BaseActivity<*>?
        get() = activity as? BaseActivity<*>

    private lateinit var baseBinding: FragmentBaseBinding

    lateinit var binding: VB
        private set
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private var onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        val rect = Rect()
        baseActivity?.window?.decorView?.getWindowVisibleDisplayFrame(rect)
        val screenHeight =
            baseActivity?.window?.decorView?.context?.resources?.displayMetrics?.heightPixels
        val keyboardHeight = screenHeight?.minus(rect.bottom) // get keyboard height
        keyboardHeight?.let {
            //if keyboard is shown then add padding that equals keyboard height
            if (it != 0) {
                if (binding.root.paddingBottom != keyboardHeight) {
                    binding.root.setPadding(0, 0, 0, keyboardHeight)
                }
            } else {
                //check if there is no padding then reset padding
                if (binding.root.paddingBottom != 0) {
                    binding.root.setPadding(0, 0, 0, 0)
                }
            }
        }
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_RecyclingCollectionSystem)
        baseBinding = FragmentBaseBinding.inflate(
            inflater.cloneInContext(contextThemeWrapper),
            container,
            false
        )
        binding = bindingInflater.invoke(
            inflater.cloneInContext(contextThemeWrapper),
            baseBinding.contentContainer,
            true
        )
        initViews()
        initActions()

        return baseBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Make parent background transparent for showing rounded corner border of header
        (view.parent as View).setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.transparent
            )
        )

        initData(arguments)
        initObservers()

        (requireActivity() as OnBackPressedDispatcherOwner)
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {
                onBackPressed()
            }
        view.setOnClickListener {
            view.hideKeyboard()
            requireActivity().currentFocus?.clearFocus()
            view.requestFocus()
        }
        baseActivity?.window?.decorView?.viewTreeObserver?.addOnGlobalLayoutListener(
            onGlobalLayoutListener
        )
    }

    override fun showLoading() {
        baseBinding.loadingView.root.isVisible = true
    }

    override fun hideLoading() {
        baseBinding.loadingView.root.isVisible = false
    }

    override fun onBackPressed() {
        if (!findNavController().popBackStack()) {
            requireActivity().finish()
        }
    }

    override fun dismiss() {
        dialog?.window?.decorView?.hideKeyboard()
        super.dismiss()
    }

    override fun onDestroyView() {
        baseActivity?.window?.decorView?.viewTreeObserver?.removeOnGlobalLayoutListener(
            onGlobalLayoutListener
        )
        super.onDestroyView()
    }
}
