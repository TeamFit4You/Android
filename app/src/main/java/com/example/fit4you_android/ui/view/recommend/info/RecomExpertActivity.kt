package com.example.fit4you_android.ui.view.recommend.info

import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityRecomExpertBinding
import com.example.fit4you_android.ui.base.BaseActivity

class RecomExpertActivity : BaseActivity<ActivityRecomExpertBinding, RecomExpertViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_recom_expert
    override val viewModel: RecomExpertViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}