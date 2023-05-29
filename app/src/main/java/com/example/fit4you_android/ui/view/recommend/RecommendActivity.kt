package com.example.fit4you_android.ui.view.recommend

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fit4you_android.ui.view.MainActivity
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityRecommendBinding
import com.example.fit4you_android.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendActivity : BaseActivity<ActivityRecommendBinding, RecommendViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_recommend
    override val viewModel: RecommendViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val email = intent.getStringExtra("email")
        val listFrag = RecommendListFragment()
        val bundle = Bundle()
        bundle.putString("email", email)
        listFrag.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.recom_frag, listFrag)
            .commit()
    }
}