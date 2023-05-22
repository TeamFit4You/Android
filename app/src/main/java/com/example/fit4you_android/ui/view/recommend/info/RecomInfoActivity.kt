package com.example.fit4you_android.ui.view.recommend.info

import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityRecomInfoBinding
import com.example.fit4you_android.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecomInfoActivity : BaseActivity<ActivityRecomInfoBinding, RecomInfoViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_recom_info
    override val viewModel: RecomInfoViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}