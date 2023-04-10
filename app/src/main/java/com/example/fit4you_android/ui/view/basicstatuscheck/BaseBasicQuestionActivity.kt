package com.example.fit4you_android.ui.view.basicstatuscheck

import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityBaseBasicQuestionBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.PhysicalFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.RomExFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.UserPainFragment

class BaseBasicQuestionActivity : BaseActivity<ActivityBaseBasicQuestionBinding, BaseBasicQuestionViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_base_basic_question
    override val viewModel: BaseBasicQuestionViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val firstFrag = PhysicalFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.basic_frag, firstFrag)
            .commit()
    }
}