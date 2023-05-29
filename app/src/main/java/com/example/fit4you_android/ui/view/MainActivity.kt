package com.example.fit4you_android.ui.view

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityMainBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.recommend.RecommendActivity
import com.example.fit4you_android.ui.view.today.TodayActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()
//    private val email = "test@email.com"
    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val email = intent.getStringExtra("email")
        Log.d("email","$email")
        binding.imgToday.setOnClickListener {
            val intent = Intent(this, TodayActivity::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }
        binding.imgRecom.setOnClickListener {
            val intent = Intent(this, RecommendActivity::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }
    }
}