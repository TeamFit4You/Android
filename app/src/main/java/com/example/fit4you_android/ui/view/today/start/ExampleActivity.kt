package com.example.fit4you_android.ui.view.today.start

import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityExampleBinding
import com.example.fit4you_android.ui.base.BaseActivity

class ExampleActivity : BaseActivity<ActivityExampleBinding, ExampleViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_example
    override val viewModel: ExampleViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}