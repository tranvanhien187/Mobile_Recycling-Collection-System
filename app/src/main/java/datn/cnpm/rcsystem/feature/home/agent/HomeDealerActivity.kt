package datn.cnpm.rcsystem.feature.home.agent

import android.view.LayoutInflater
import datn.cnpm.rcsystem.base.BaseActivity
import datn.cnpm.rcsystem.databinding.ActivityHomeDealerBinding


class HomeDealerActivity : BaseActivity<ActivityHomeDealerBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeDealerBinding
        get() = ActivityHomeDealerBinding::inflate
}