package datn.cnpm.rcsystem.feature.history

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import datn.cnpm.rcsystem.feature.history.garbage.GarbageHistoryFragment
import datn.cnpm.rcsystem.feature.history.gift.GiftHistoryFragment


class HistoryScreenAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        return when (position) {
            0 -> GiftHistoryFragment()
            1 -> GarbageHistoryFragment()
            else -> GiftHistoryFragment()
        }
    }
}