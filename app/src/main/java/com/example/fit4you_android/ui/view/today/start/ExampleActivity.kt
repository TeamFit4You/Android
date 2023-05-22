package com.example.fit4you_android.ui.view.today.start

import android.content.Intent
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityExampleBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.today.TodayActivity

class ExampleActivity : BaseActivity<ActivityExampleBinding, ExampleViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_example
    override val viewModel: ExampleViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val bodyPart = intent.getStringExtra("key")
        binding.tbExamPart.text = bodyPart

        binding.btnExamPrev.setOnClickListener {
            val intent = Intent(this, TodayActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnExamNext.setOnClickListener {
            val intent = Intent(this, SelfActivity::class.java)
            intent.putExtra("tool", bodyPart)
            startActivity(intent)
        }
    }
}