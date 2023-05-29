package com.example.fit4you_android.ui.view.today

import android.os.Bundle
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityTodayBinding
import com.example.fit4you_android.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayActivity : BaseActivity<ActivityTodayBinding, TodayViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_today
    override val viewModel: TodayViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val email = intent.getStringExtra("email")
        val listFrag = TodayListFragment()
        val bundle = Bundle()
        bundle.putString("email", email)
        listFrag.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.today_frag, listFrag)
            .commit()
    }
}