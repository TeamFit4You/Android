package com.example.fit4you_android.ui.view.basicstatuscheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityBaseBasicQuestionBinding
import com.example.fit4you_android.ui.base.BaseActivity

class BaseBasicQuestionActivity : BaseActivity<ActivityBaseBasicQuestionBinding, BaseBasicQuestionViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_base_basic_question
    override val viewModel: BaseBasicQuestionViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}