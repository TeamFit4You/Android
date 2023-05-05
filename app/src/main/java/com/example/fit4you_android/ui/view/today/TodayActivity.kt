package com.example.fit4you_android.ui.view.today

import android.content.Intent
import androidx.activity.viewModels
import com.example.fit4you_android.MainActivity
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
        val listFrag = TodayListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.today_frag, listFrag)
            .commit()

        binding.btnTodayPrev.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}