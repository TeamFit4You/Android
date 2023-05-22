package com.example.fit4you_android.ui.view.today.start

import android.content.Intent
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivitySelfBinding
import com.example.fit4you_android.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfActivity : BaseActivity<ActivitySelfBinding, SelfViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_self
    override val viewModel: SelfViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val bodyPart = intent.getStringExtra("key")
        binding.tbSelfPart.text = bodyPart

        binding.btnSelfPrev.setOnClickListener {
            val intent = Intent(this, ExampleActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSelfNext.setOnClickListener {

        }
    }
}