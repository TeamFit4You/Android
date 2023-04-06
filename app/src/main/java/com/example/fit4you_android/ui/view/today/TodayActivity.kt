package com.example.fit4you_android.ui.view.today

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityTodayBinding
import com.example.fit4you_android.ui.base.BaseActivity

class TodayActivity : BaseActivity<ActivityTodayBinding, TodayViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_today
    override val viewModel: TodayViewModel by viewModels()
    override fun initView() {
        val listFrag = TodayListFragment()
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.today_frag, listFrag)
            .commit()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}