package datn.cnpm.rcsystem.feature.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import datn.cnpm.rcsystem.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import datn.cnpm.rcsystem.common.extension.makeStatusBarTransparent
import datn.cnpm.rcsystem.databinding.ActivityAuthenticationBinding

@AndroidEntryPoint
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityAuthenticationBinding
        get() = ActivityAuthenticationBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.makeStatusBarTransparent()
        Log.d("TAGAA", "onCreate: ")

    }

    override fun onStart() {
        super.onStart()
        Log.d("TAGAA", "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("TAGAA", "onRestart: ")

    }

    override fun onPause() {
        super.onPause()
        Log.d("TAGAA", "onPause: ")

    }

    override fun onResume() {
        super.onResume()
        Log.d("TAGAA", "onResume: ")

    }

    override fun onStop() {
        super.onStop()
        Log.d("TAGAA", "onStop: ")

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAGAA", "onDestroy: ")

    }
}