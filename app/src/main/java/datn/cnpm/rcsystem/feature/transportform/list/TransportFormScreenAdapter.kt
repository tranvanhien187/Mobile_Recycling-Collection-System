package datn.cnpm.rcsystem.feature.transportform.list

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import datn.cnpm.rcsystem.feature.transportform.garbage.GarbageFormFragment
import datn.cnpm.rcsystem.feature.transportform.gift.GiftFormFragment


class TransportFormScreenAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        return when (position) {
            0 -> GiftFormFragment()
            1 -> GarbageFormFragment()
            else -> GiftFormFragment()
        }
    }
}