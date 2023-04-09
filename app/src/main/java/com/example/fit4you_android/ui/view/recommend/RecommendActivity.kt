package com.example.fit4you_android.ui.view.recommend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityRecommendBinding
import com.example.fit4you_android.ui.base.BaseActivity

class RecommendActivity : BaseActivity<ActivityRecommendBinding, RecommendViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_recommend
    override val viewModel: RecommendViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val listFrag = RecommendListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.recom_frag, listFrag)
            .commit()
    }
}