package com.example.fit4you_android.ui.view.recommend.info

import android.content.Intent
import androidx.activity.viewModels
import com.example.fit4you_android.Fit4YouApp
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
        val token = Fit4YouApp.prefs.getString("accessToken", "")
        val bodyPart = intent.getStringExtra("key")
        val exerciseId = intent.getLongExtra("exerciseId", 0)
        val detail = intent.getStringExtra("detail")
        binding.tbRecomInfo.text = bodyPart

        binding.btnRecomInfoNext.setOnClickListener {
            val intent = Intent(this, RecomExpertActivity::class.java)
            intent.putExtra("key", bodyPart)
            intent.putExtra("exerciseId", exerciseId)
            intent.putExtra("detail", detail)
            startActivity(intent)
        }
    }
}